package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.Filter;
import org.fk.core.util.query.FilterOperator;
import org.fk.core.util.query.QueryParameters;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.product.dao.DAOFactory;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.request.RequestContext;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.repository.ProductRepository;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.exception.DataAccessException;
import org.fk.core.manager.AbstractManager;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.jooq.impl.DSL.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class ProductManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(ProductManager.class);

    @Inject
    DSLFactory dslFactory;

    @Inject
    DAOFactory daoFactory;

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> testMultiset(final RequestContext request) {
        DSLContext dsl = dslFactory.create(request);
        ProductDAO productViewDAO = daoFactory.createProductDAO(dsl);

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


    public ProductPaginateDTO query(final RequestContext request, final QueryParameters queryParameters) throws InvalidDataException {
        DSLContext dsl = dslFactory.create(request);
        return productRepository.paginate(request, dsl, queryParameters);
    }

    public Optional<ProductDTO> getOne(final RequestContext request, final Long productId) throws DataAccessException {
        DSLContext dsl = dslFactory.create(request);
        return productRepository.fetchOne(request, dsl, productId);
    }

    public void testMultiTransaction(final RequestContext request) {

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
            dslFactory.create(request).transaction(tx1 -> {
                // transaction1
                Filter filter1 = new Filter("productId", FilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                Filter filter2 = new Filter("productId", FilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                QueryParameters queryParameters = new QueryParameters();
                queryParameters.setPage(0);
                queryParameters.setPageSize(1000);
                queryParameters.getFilters().add(filter1);
                queryParameters.getFilters().add(filter2);

                ProductPaginateDTO productPaginate = productRepository.paginate(request, tx1.dsl(), queryParameters);

                tx1.dsl().transaction(tx2 -> {
                    // transaction2
                    ProductDAO aProductRecordDAO = daoFactory.createProductDAO(tx2.dsl());
                    aProductRecordDAO.deleteDTOs(productPaginate.getProducts());
                });

                try {
                    tx1.dsl().transaction(tx3 -> {
                        // transaction3
                        ProductDAO bProductRecordDAO = daoFactory.createProductDAO(tx3.dsl());
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
    public ProductDTO create(final RequestContext request, final ProductDTO product) throws ValidationException {
        DSLContext dsl = dslFactory.create(request);
        ProductDAO productRecordDAO = daoFactory.createProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = daoFactory.createProductLangDAO(dsl);

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
    public ProductDTO update(final RequestContext request, final ProductDTO product) throws ValidationException {
        DSLContext dsl = dslFactory.create(request);
        ProductDAO productRecordDAO = daoFactory.createProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = daoFactory.createProductLangDAO(dsl);

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
    public void delete(final RequestContext request, final ProductDTO product) {
        DSLContext dsl = dslFactory.create(request);
        ProductDAO productRecordDAO = daoFactory.createProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = daoFactory.createProductLangDAO(dsl);

        // we do use the explicit delete-by-id methods here, because they are the most performant.
        productLangRecordDAO.deleteByProductId(product.getProductId());
        productRecordDAO.deleteById(product.getProductId());
    }

    /**
     * Trying out streaming
     *
     * @param request request
     * @return stream
     */
    public Stream<ProductDTO> streamAll(final RequestContext request) {
        DSLContext dsl = dslFactory.create(request);
        ProductLangDAO productLangRecordDAO = daoFactory.createProductLangDAO(dsl);

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
            return productRepository.fetch(request, dsl, ids);
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

}
