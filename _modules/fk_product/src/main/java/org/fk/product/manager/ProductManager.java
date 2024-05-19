package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkFilter;
import org.fk.core.query.model.FkFilterOperator;
import org.fk.core.query.model.FkQuery;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.core.exception.ValidationException;
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
        List<Field<?>> fields = new ArrayList<>(List.of(table().fields()));

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

    public ProductPaginateDTO query(DSLContext dsl, final FkQuery fkQuery) throws InvalidDataException {
        final ProductRepository productRepository = new ProductRepository(dsl);

        int count = productRepository.count(fkQuery.getFilters());
        List<ProductDTO> products = productRepository.query(fkQuery);

        ProductPaginateDTO paginate = new ProductPaginateDTO();
        paginate.setProducts(products);
        paginate.setCount(count);

        // test localization here.
        Locale locale = Locale.of("en");
        String localizationTest = ResourceBundle.getBundle("messages", locale).getString("product.paginate.localizationTest");
        paginate.setLocalizationTest(localizationTest);

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

        List<IProduct> inserts = new ArrayList<>();
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
                FkFilter filter1 = new FkFilter("productId", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                FkFilter filter2 = new FkFilter("productId", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                FkQuery fkQuery = new FkQuery();
                fkQuery.setPage(0);
                fkQuery.setPageSize(1000);
                fkQuery.getFilters().add(filter1);
                fkQuery.getFilters().add(filter2);

                final ProductRepository productRepository = new ProductRepository(tx1.dsl());
                List<ProductDTO> products = productRepository.query(fkQuery);

                tx1.dsl().transaction(tx2 -> {
                    // transaction2
                    ProductDAO aProductRecordDAO = new ProductDAO(tx2.dsl());
                    aProductRecordDAO.deleteById(products.stream().map(ProductDTO::getProductId).toList());
                });

                try {
                    tx1.dsl().transaction(tx3 -> {
                        // transaction3
                        ProductDAO bProductRecordDAO = new ProductDAO(tx3.dsl());
                        bProductRecordDAO.insert(inserts);
                    });
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            });
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public ProductDTO create(DSLContext dsl, final ProductDTO product) throws ValidationException {
        try {
            return dsl.transactionResult(tx1 -> {
                ProductDAO productRecordDAO = new ProductDAO(dsl);
                ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

                // we first use insertAndReturn() to insert the product and get the autoincrement-id for it
                // afterwards we use insert() to insert the productLanguages in the most performant (batching) way.
                this.validateInsert(product);
                productRecordDAO.insert(product);

                List<IProductLang> productLangInserts = new ArrayList<>();
                for (ProductLangDTO xLang : product.getLangs()) {
                    xLang.setProductId(product.getProductId());
                    this.validateInsert(xLang);
                    productLangInserts.add(xLang);
                }
                productLangRecordDAO.insert(productLangInserts);

                return new ProductRepository(dsl).fetch(product.getProductId());
            });
        } catch (Exception e) {
            if (e.getCause() instanceof ValidationException ve) {
                throw ve;
            }
            throw e;
        }
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public ProductDTO update(DSLContext dsl, final ProductDTO product) throws ValidationException {
        ProductDAO productRecordDAO = new ProductDAO(dsl);
        ProductLangDAO productLangRecordDAO = new ProductLangDAO(dsl);

        this.validateUpdate(product);
        productRecordDAO.update(product);

        List<IProductLang> insertXLangs = new ArrayList<>();
        List<IProductLang> updateXLangs = new ArrayList<>();
        List<IProductLang> deleteXLangs = new ArrayList<>();
        for (ProductLangDTO xLang : product.getLangs()) {
            xLang.setProductId(product.getProductId());
            if (xLang.getDeleteFlag() != null && xLang.getDeleteFlag()) {
                deleteXLangs.add(xLang);
            } else if (xLang.getInsertFlag() != null && xLang.getInsertFlag()) {
                insertXLangs.add(xLang);
            } else {
                updateXLangs.add(xLang);
            }
            this.validateUpdate(xLang);
        }
        productLangRecordDAO.delete(deleteXLangs);
        productLangRecordDAO.insert(insertXLangs);
        productLangRecordDAO.update(updateXLangs);

        return new ProductRepository(dsl).fetch(product.getProductId());
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
        FkQuery fkQuery = new FkQuery();
        fkQuery.setPage(0);
        fkQuery.setPageSize(100000);

        final ProductRepository productRepository = new ProductRepository(dsl);
        Stream<ProductDTO> stream1 = productRepository.stream(fkQuery);
        Stream<List<ProductDTO>> chunkStream = chunk(stream1, 250);

        // the "parallel" is important here, as it really pushes performance.
        return chunkStream.parallel().flatMap(List::stream);
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size chunk-size of each chunk
     * @return stream of list of items.
     */
    Stream<List<ProductDTO>> chunk(Stream<ProductDTO> stream, int size) {
        Iterator<ProductDTO> iterator = stream.iterator();
        Iterator<List<ProductDTO>> listIterator = new Iterator<>() {

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<ProductDTO> next() {
                List<ProductDTO> result = new ArrayList<>(size);
                for (int i = 0; i < size && iterator.hasNext(); i++) {
                    result.add(iterator.next());
                }
                return result;
            }
        };
        return StreamSupport.stream(((Iterable<List<ProductDTO>>) () -> listIterator).spliterator(), false);
    }

}
