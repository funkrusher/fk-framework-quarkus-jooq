package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.core.jooq.DSLFactory;
import org.fk.core.jooq.RequestContext;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.Filter;
import org.fk.core.util.query.FilterOperator;
import org.fk.core.util.query.QueryParameters;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.core.util.exception.ValidationException;
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


    public List<ProductDTO> testMultiset(DSLContext dsl) {
        ProductDAO productViewDAO = new ProductDAO(dsl);

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

    public ProductPaginateDTO query(DSLContext dsl, final QueryParameters queryParameters) throws InvalidDataException {
        final ProductRepository productRepository = new ProductRepository(dsl);

        int count = productRepository.count(queryParameters);
        List<Long> productIds = productRepository.paginate(queryParameters);
        List<ProductDTO> products = productRepository.fetch(productIds);

        ProductPaginateDTO paginate = new ProductPaginateDTO();
        paginate.setProducts(products);
        paginate.setCount(count);
        return paginate;
    }

    public Optional<ProductDTO> getOne(DSLContext dsl, final Long productId) throws DataAccessException {
        final ProductRepository productRepository = new ProductRepository(dsl);
        List<ProductDTO> result = productRepository.fetch(List.of(productId));
        if (result == null || result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result.getFirst());
        }
    }

    public void testMultiTransaction(DSLContext dsl) {

        // we use jooq transactions, because they are more fine-tuneable.
        // see: https://blog.jooq.org/nested-transactions-in-jooq/

        ProductDTO p = new ProductDTO();
        p.setProductId(3L);

        List<ProductRecord> inserts = new ArrayList<>();
        for (int i= 0; i < 1000; i++) {
            ProductRecord insert1 = new ProductRecord();
            insert1.setProductId(90000000L + i);
            insert1.setClientId(1);
            insert1.setPrice(new BigDecimal("12.21"));
            inserts.add(insert1);
        }

        try {
            dsl.transaction(tx1 -> {
                // transaction1
                Filter filter1 = new Filter("productId", FilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                Filter filter2 = new Filter("productId", FilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                QueryParameters queryParameters = new QueryParameters();
                queryParameters.setPage(0);
                queryParameters.setPageSize(1000);
                queryParameters.getFilters().add(filter1);
                queryParameters.getFilters().add(filter2);

                final ProductRepository productRepository = new ProductRepository(tx1.dsl());
                List<Long> productIds = productRepository.paginate(queryParameters);

                tx1.dsl().transaction(tx2 -> {
                    // transaction2
                    ProductDAO aProductRecordDAO = new ProductDAO(tx2.dsl());
                    aProductRecordDAO.deleteById(productIds);
                });

                try {
                    tx1.dsl().transaction(tx3 -> {
                        // transaction3
                        ProductDAO bProductRecordDAO = new ProductDAO(tx3.dsl());
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
    public ProductDTO create(DSLContext dsl, final ProductDTO product) throws ValidationException {
        ProductDAO productRecordDAO = new ProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

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
    public ProductDTO update(DSLContext dsl, final ProductDTO product) throws ValidationException {
        ProductDAO productRecordDAO = new ProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

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
    public void delete(DSLContext dsl, final ProductDTO product) {
        ProductDAO productRecordDAO = new ProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

        // we do use the explicit delete-by-id methods here, because they are the most performant.
        productLangRecordDAO.deleteByProductId(product.getProductId());
        productRecordDAO.deleteById(product.getProductId());
    }

    /**
     * Trying out streaming
     * @return stream
     */
    public Stream<ProductDTO> streamAll(DSLContext dsl) throws InvalidDataException {
        ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

        QueryParameters queryParameters = new QueryParameters();
        queryParameters.setPage(0);
        queryParameters.setPageSize(100000);

        final ProductRepository productRepository = new ProductRepository(dsl);
        Stream<Long> stream1 = productRepository.stream(queryParameters);
        Stream<List<Long>> chunkStream = chunk(stream1, 250);

        // the "parallel" is important here, as it really pushes performance.
        return chunkStream.parallel().map(productRepository::fetch).flatMap(List::stream);
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size chunk-size of each chunk
     * @return stream of list of items.
     */
    Stream<List<Long>> chunk(Stream<Long> stream, int size) {
        Iterator<Long> iterator = stream.iterator();
        Iterator<List<Long>> listIterator = new Iterator<>() {

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<Long> next() {
                List<Long> result = new ArrayList<>(size);
                for (int i = 0; i < size && iterator.hasNext(); i++) {
                    result.add(iterator.next());
                }
                return result;
            }
        };
        return StreamSupport.stream(((Iterable<List<Long>>) () -> listIterator).spliterator(), false);
    }

}
