package org.fk.core.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.core.jooq.JooqContext;
import org.fk.core.jooq.JooqContextFactory;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.Filter;
import org.fk.core.util.query.FilterOperator;
import org.fk.core.util.query.QueryParameters;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.dao.DAOFactory;
import org.fk.core.dao.record.ProductLangRecordDAO;
import org.fk.core.dao.record.ProductRecordDAO;
import org.fk.core.dao.view.ProductViewDAO;
import org.fk.core.dto.ProductDTO;
import org.fk.core.dto.ProductLangDTO;
import org.fk.core.util.exception.ValidationException;
import org.fk.core.util.request.RequestContext;
import org.jboss.logging.Logger;
import org.jooq.Field;
import org.jooq.exception.DataAccessException;

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
    JooqContextFactory jooqContextFactory;

    @Inject
    DAOFactory daoFactory;

    public List<ProductDTO> testMultiset(final RequestContext requestContext) {
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(jooqContext);

        List<Field<?>> fields = new ArrayList<>();
        fields.addAll(List.of(table().fields()));
        fields.add(multiset(
                selectDistinct(asterisk())
                        .from(ProductLang.PRODUCT_LANG)
                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
        ).as("langs"));

        return jooqContext.getCtx()
                .select(fields)
                .from(Product.PRODUCT)
                .limit(10)
                .fetchInto(ProductDTO.class);
    }


    public List<ProductDTO> query(final RequestContext requestContext, final QueryParameters queryParameters) throws InvalidDataException {
        // we use the request-scoped dsl-context as source for the configuration of the dao.
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(jooqContext);
        return productViewDAO.query(queryParameters);
    }

    public Optional<ProductDTO> getOne(final RequestContext requestContext, final Long productId) throws DataAccessException {
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(jooqContext);
        return productViewDAO.findOptionalById(productId);
    }

    public void testMultiTransaction(final RequestContext requestContext) {

        // we use jooq transactions, because they are more fine-tuneable.
        // see: https://blog.jooq.org/nested-transactions-in-jooq/

        List<ProductDTO> inserts = new ArrayList<>();
        for (int i= 0; i < 1000; i++) {
            ProductDTO insert1 = new ProductDTO();
            insert1.setProductId(90000000L + i);
            insert1.setClientId(1);
            insert1.setPrice(new BigDecimal("12.21"));
            inserts.add(insert1);
        }

        try {
            jooqContextFactory.withinTransaction(requestContext, jooqContext -> {
                Filter filter1 = new Filter("productId", FilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                Filter filter2 = new Filter("productId", FilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                QueryParameters queryParameters = new QueryParameters();
                queryParameters.setPage(0);
                queryParameters.setPageSize(1000);
                queryParameters.getFilters().add(filter1);
                queryParameters.getFilters().add(filter2);

                ProductViewDAO productViewDAO = daoFactory.createProductViewDAO(jooqContext);
                List<ProductDTO> queryResult = productViewDAO.query(queryParameters);

                jooqContextFactory.withinTransaction(requestContext, subContextA -> {
                    ProductRecordDAO aProductRecordDAO = daoFactory.createProductRecordDAO(subContextA);
                    aProductRecordDAO.deleteDTOs(queryResult);
                });

                try {
                    jooqContextFactory.withinTransaction(requestContext, subContextB -> {
                        ProductRecordDAO bProductRecordDAO = daoFactory.createProductRecordDAO(subContextB);
                        bProductRecordDAO.insertDTOs(inserts);
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
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(jooqContext);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(jooqContext);

        // we first use insertAndReturn() to insert the product and get the autoincrement-id for it
        // afterwards we use insert() to insert the productLanguages in the most performant (batching) way.
        this.validateDTO(product);
        productRecordDAO.insertAndReturnDTO(product);
        for (ProductLangDTO xLang : product.getLangs()) {
            xLang.setProductId(product.getProductId());
            this.validateDTO(xLang);
        }
        productLangRecordDAO.insertDTOs(product.getLangs());
        return product;
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO update(final RequestContext requestContext, final ProductDTO product) {
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(jooqContext);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(jooqContext);

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
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductRecordDAO productRecordDAO = daoFactory.createProductRecordDAO(jooqContext);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(jooqContext);

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
        JooqContext jooqContext = jooqContextFactory.createJooqContext(requestContext);
        ProductLangRecordDAO productLangRecordDAO = daoFactory.createProductLangRecordDAO(jooqContext);

        // TODO: try to find how this can be put into the Abstraction,
        Stream<ProductRecord> stream1 = jooqContext.getCtx()
                .selectFrom(Product.PRODUCT)
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
}