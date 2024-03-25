package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.core.dao.RecordToViewMapper;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.Filter;
import org.fk.core.util.query.FilterOperator;
import org.fk.core.util.query.QueryParameters;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.product.dao.DAOFactory;
import org.fk.product.dao.ProductLangRecordDAO;
import org.fk.product.dao.ProductRecordDAO;
import org.fk.product.dao.ProductViewDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.fk.core.manager.AbstractBaseManager;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.jooq.impl.DSL.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class ProductManager extends AbstractBaseManager {

    private static final Logger LOGGER = Logger.getLogger(ProductManager.class);

    @Inject
    DSLFactory dslFactory;

    @Inject
    DAOFactory daoFactory;

    public List<ProductDTO> testMultiset(final RequestContext requestContext) {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(dsl);

        List<Field<?>> fields = new ArrayList<>();
        fields.addAll(List.of(table().fields()));

        // note: for MULTISET to work, we need to activate allowMultiQueries=true in mariadb via jdbc-url.
        // see: https://blog.jooq.org/mysqls-allowmultiqueries-flag-with-jdbc-and-jooq/
        fields.add(multiset(
                selectDistinct(asterisk())
                        .from(ProductLang.PRODUCT_LANG)
                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
        ).as("langs"));

        return dsl.select(fields)
                .from(Product.PRODUCT)
                .limit(10)
                .fetchInto(ProductDTO.class);
    }


    public List<ProductDTO> query(final RequestContext requestContext, final QueryParameters queryParameters) throws InvalidDataException {
        // we use the request-scoped dsl-context as source for the configuration of the dao.
        DSLContext dsl = dslFactory.create(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(dsl);
        Result<Record> result = productViewDAO.query(queryParameters);
        return recordsToView(requestContext, result);
    }

    public Optional<ProductDTO> getOne(final RequestContext requestContext, final Long productId) throws DataAccessException {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(dsl);

        Result<Record> result = productViewDAO.findById(productId);
        if (result == null) {
            return Optional.empty();
        } else {
            return Optional.of(recordsToView(requestContext, result).getFirst());
        }
    }

    public void testMultiTransaction(final RequestContext requestContext) {

        // we use jooq transactions, because they are more fine-tuneable.
        // see: https://blog.jooq.org/nested-transactions-in-jooq/

        List<ProductRecord> inserts = new ArrayList<>();
        for (int i= 0; i < 1000; i++) {
            ProductRecord insert1 = new ProductRecord();
            insert1.setProductId(90000000L + i);
            insert1.setClientId(1);
            insert1.setPrice(new BigDecimal("12.21"));
            inserts.add(insert1);
        }

        try {
            final DSLContext dsl = dslFactory.create(requestContext);
            dslFactory.create(requestContext).transaction(tx1 -> {
                // transaction1
                Filter filter1 = new Filter("productId", FilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                Filter filter2 = new Filter("productId", FilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                QueryParameters queryParameters = new QueryParameters();
                queryParameters.setPage(0);
                queryParameters.setPageSize(1000);
                queryParameters.getFilters().add(filter1);
                queryParameters.getFilters().add(filter2);

                ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(tx1.dsl());
                Result<Record> queryResult = productViewDAO.query(queryParameters);
                List<ProductDTO> existingProducts = recordsToView(requestContext, queryResult);

                tx1.dsl().transaction(tx2 -> {
                    // transaction2
                    ProductRecordDAO aProductRecordDAO = daoFactory.createProductRecordDAO(tx2.dsl());
                    aProductRecordDAO.deleteDTOs(existingProducts);
                });

                try {
                    tx1.dsl().transaction(tx3 -> {
                        // transaction3
                        ProductRecordDAO bProductRecordDAO = daoFactory.createProductRecordDAO(tx3.dsl());
                        bProductRecordDAO.insert(inserts);
                        // Integer x = Integer.valueOf("test");
                    });
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO create(final RequestContext requestContext, final ProductDTO product) throws ValidationException {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(dsl);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(dsl);

        // we first use insertAndReturn() to insert the product and get the autoincrement-id for it
        // afterwards we use insert() to insert the productLanguages in the most performant (batching) way.
        this.validateInsert(product);
        productRecordDAO.insertAndReturnDTO(product);
        for (ProductLangDTO xLang : product.getLangs()) {
            xLang.setProductId(product.getProductId());
            this.validateInsert(xLang);
        }
        productLangRecordDAO.insertDTOs(product.getLangs());
        return product;
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO update(final RequestContext requestContext, final ProductDTO product) throws ValidationException {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(dsl);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(dsl);

        this.validateUpdate(product);
        productRecordDAO.updateDTO(product);

        List<ProductLangDTO> insertXLangs = new ArrayList<>();
        List<ProductLangDTO> updateXLangs = new ArrayList<>();
        List<ProductLangDTO> deleteXLangs = new ArrayList<>();
        for (ProductLangDTO xLang : product.getLangs()) {
            xLang.setProductId(product.getProductId());
            if (xLang.getDeleteFlag()) {
                deleteXLangs.add(xLang);
            } else if (xLang.getInsertFlag()) {
                insertXLangs.add(xLang);
            } else {
                updateXLangs.add(xLang);
            }
            this.validateUpdate(xLang);
        }
        productLangRecordDAO.deleteDTOs(deleteXLangs);
        productLangRecordDAO.insertDTOs(insertXLangs);
        productLangRecordDAO.updateDTOs(updateXLangs);
        return product;
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public void delete(final RequestContext requestContext, final ProductDTO product) {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(dsl);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(dsl);

        // we do use the explicit delete-by-id methods here, because they are the most performant.
        productLangRecordDAO.deleteByProductId(product.getProductId());
        productRecordDAO.deleteById(product.getProductId());
    }

    /**
     * Trying out streaming
     *
     * @param requestContext requestContext
     * @return stream
     */
    public Stream<ProductDTO> streamAll(final RequestContext requestContext) {
        DSLContext dsl = dslFactory.create(requestContext);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(dsl);

        // TODO: try to find how this can be put into the Abstraction,
        Stream<ProductRecord> stream1 = dsl.selectFrom(Product.PRODUCT)
                .fetchSize(250)
                .fetchStream();
        Stream<List<ProductRecord>> chunkStream = chunk(stream1, 250);

        // the "parallel" is important here, as it really pushes performance.
        return chunkStream.parallel().map(records -> {
            List<Long> ids = new ArrayList<>();
            for (ProductRecord record : records) {
                ids.add(record.getProductId());
            }
            List<ProductLangRecord> xLangs = productLangRecordDAO.fetchAllByProductsIds(ids);

            List<ProductDTO> products = new ArrayList<>();
            for (ProductRecord record : records) {
                ProductDTO product = record.into(new ProductDTO());

                List<ProductLangDTO> productLangs = new ArrayList<>();
                for (ProductLangRecord lang : xLangs) {
                    ProductLangDTO productLang = lang.into(new ProductLangDTO());
                    if (productLang.getProductId().equals(product.getProductId())) {
                        productLangs.add(productLang);
                        if (productLang.getLangId().equals(requestContext.getLangId())) {
                            product.setLang(productLang);
                        }
                    }
                }
                product.setLangs(productLangs);
                products.add(product);
            }
            return products;
        }).flatMap(List::stream);
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size chunk-size of each chunk
     * @return stream of list of items.
     */
    Stream<List<ProductRecord>> chunk(Stream<ProductRecord> stream, int size) {
        Iterator<ProductRecord> iterator = stream.iterator();
        Iterator<List<ProductRecord>> listIterator = new Iterator<>() {

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<ProductRecord> next() {
                List<ProductRecord> result = new ArrayList<>(size);
                for (int i = 0; i < size && iterator.hasNext(); i++) {
                    result.add(iterator.next());
                }
                return result;
            }
        };
        return StreamSupport.stream(((Iterable<List<ProductRecord>>) () -> listIterator).spliterator(), false);
    }




    private final RecordToViewMapper<ProductDTO, ProductRecord, Long> productViewMapper = new RecordToViewMapper<>(
            ProductDTO.class,
            ProductRecord.class,
            Product.PRODUCT.PRODUCTID,
            List.of(Product.PRODUCT.PRODUCTID));

    private final RecordToViewMapper<ProductLangDTO, ProductLangRecord, Long> productLangViewMapper = new RecordToViewMapper<>(
            ProductLangDTO.class,
            ProductLangRecord.class,
            ProductLang.PRODUCT_LANG.PRODUCTID,
            List.of(ProductLang.PRODUCT_LANG.PRODUCTID, ProductLang.PRODUCT_LANG.LANGID));

    /**
     * Define the mapping of the resulting records to the DTOs that this view is returning.
     *
     * @param records resulting records containing joined table data.
     * @return DTOs
     */
    protected List<ProductDTO> recordsToView(RequestContext requestContext, List<Record> records) {
        final List<ProductDTO> products = productViewMapper.extractRecords(records);
        final Map<Long, List<ProductLangDTO>> langs = productLangViewMapper.extractRecordsGroupedBy(records);
        for (ProductDTO product : products) {
            final List<ProductLangDTO> productLangs = langs.getOrDefault(product.getProductId(), new ArrayList<>());
            product.setLangs(productLangs);
            for (ProductLangDTO productLang : productLangs) {
                if (productLang.getLangId().equals(requestContext.getLangId())) {
                    product.setLang(productLang);
                }
            }
        }
        return products;
    }


}
