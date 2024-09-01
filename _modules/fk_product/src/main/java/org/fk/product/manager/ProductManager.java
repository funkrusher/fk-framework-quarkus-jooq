package org.fk.product.manager;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.MessageBundles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.fk.core.request.RequestContext;
import org.fk.core.transfer.csv.CsvWriter;
import org.fk.core.transfer.json.JsonWriter;
import org.fk.core.transfer.pdf.PdfWriter;
import org.fk.core.transfer.xlsx.XlsxWriter;
import org.fk.database1.Database1;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkFilter;
import org.fk.core.query.model.FkFilterOperator;
import org.fk.core.query.model.FkQuery;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.dtos.ProductDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dao.ProductDAO;
import org.fk.product.api.ProductAPI;
import org.fk.product.api.ProductLangAPI;
import org.fk.core.exception.ValidationException;
import org.fk.product.api.ProductPaginateAPI;
import org.fk.product.qute.ProductMessages;
import org.fk.product.repository.ProductRepository;
import org.fk.product.qute.ProductTemplates;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.exception.DataAccessException;
import org.fk.core.manager.AbstractManager;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.jooq.impl.DSL.*;

/**
 * ProductManager
 */
@ApplicationScoped
public class ProductManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(ProductManager.class);

    @Inject
    Database1 database1;

    public List<ProductAPI> testMultiset(RequestContext requestContext) {
        List<Field<?>> fields = new ArrayList<>(List.of(table().fields()));

        // note: for MULTISET to work, we need to activate allowMultiQueries=true in mariadb via jdbc-url.
        // see: https://blog.jooq.org/mysqls-allowmultiqueries-flag-with-jdbc-and-jooq/
        fields.add(multiset(
                selectDistinct(asterisk())
                        .from(ProductLang.PRODUCT_LANG)
                        .where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(Product.PRODUCT.PRODUCTID))
        ).as("langs"));

        return database1.dsl(requestContext).transactionResult(tsx -> {
            return tsx.dsl().select(fields)
                .from(Product.PRODUCT)
                .limit(10)
                .fetchInto(ProductAPI.class);
        });
    }

    public ProductPaginateAPI query(RequestContext requestContext, final FkQuery fkQuery) throws InvalidDataException {
        return database1.dsl(requestContext).transactionResult(tsx -> {
            final ProductRepository repo = new ProductRepository(tsx.dsl());

            List<ProductAPI> products = repo.query(repo::getFullQuery, fkQuery);
            int count = repo.count(repo::getFullQuery, fkQuery.getFilters());

            ProductPaginateAPI paginate = new ProductPaginateAPI();
            paginate.setProducts(products);
            paginate.setCount(count);

            // test localization here.
            Locale locale = Locale.GERMANY;
            ProductMessages messages = MessageBundles.get(ProductMessages.class, Localized.Literal.of(locale.toLanguageTag()));
            String localizationTest = messages.product_paginate_localizationTest();
            paginate.setLocalizationTest(localizationTest);

            return paginate;
        });
    }

    public Optional<ProductAPI> getOne(RequestContext requestContext, final Long productId) throws DataAccessException {
        return database1.dsl(requestContext).transactionResult(tsx -> {
            final ProductRepository repo = new ProductRepository(tsx.dsl());
            ProductAPI result = repo.fetch(repo::getFullQuery, productId);
            if (result == null) {
                return Optional.empty();
            } else {
                return Optional.of(result);
            }
        });
    }

    public void testMultiTransaction(RequestContext requestContext) {
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
            database1.dsl(requestContext).transaction(tsx -> {
                // transaction1
                FkFilter filter1 = new FkFilter("productId", FkFilterOperator.GREATER_THAN_OR_EQUALS, List.of("90000000"));
                FkFilter filter2 = new FkFilter("productId", FkFilterOperator.LESS_THAN_OR_EQUALS, List.of("90050000"));

                FkQuery fkQuery = new FkQuery();
                fkQuery.setPage(0);
                fkQuery.setPageSize(1000);
                fkQuery.getFilters().add(filter1);
                fkQuery.getFilters().add(filter2);

                final ProductRepository repo = new ProductRepository(tsx.dsl());
                List<ProductAPI> products = repo.query(repo::getFullQuery, fkQuery);

                tsx.dsl().transaction(tx2 -> {
                    // transaction2
                    ProductDAO aProductRecordDAO = new ProductDAO(tsx.dsl());
                    aProductRecordDAO.deleteById(products.stream().map(ProductAPI::getProductId).toList());
                });

                try {
                    tsx.dsl().transaction(tx3 -> {
                        // transaction3
                        ProductDAO bProductRecordDAO = new ProductDAO(tsx.dsl());
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

    public ProductDto create(RequestContext requestContext, final ProductDto product) throws ValidationException {
        try {
            return database1.dsl(requestContext).transactionResult(tsx -> {
                ProductDAO productRecordDAO = new ProductDAO(tsx.dsl());
                this.validateInsert(product);
                productRecordDAO.insert(product);
                return product;
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
    public ProductDto update(RequestContext requestContext, final ProductDto product) throws ValidationException {
        DSLContext dsl = database1.dsl(requestContext);

        ProductDAO productRecordDAO = new ProductDAO(dsl);

        this.validateUpdate(product);
        productRecordDAO.update(product);
        return product;
    }

    // it almost made me laugh out of bitterness, that @Transactional does not catch checked-exceptions per default
    // can we please not! use it?
    // see: https://github.com/quarkusio/quarkus/issues/34569
    @Transactional(rollbackOn = Exception.class)
    public void delete(RequestContext requestContext, final ProductAPI product) {
        DSLContext dsl = database1.dsl(requestContext);

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
    public Stream<ProductAPI> streamAll(RequestContext requestContext) throws InvalidDataException {
        FkQuery fkQuery = new FkQuery();
        fkQuery.setPage(0);
        fkQuery.setPageSize(100000);

        return database1.dsl(requestContext).transactionResult(tsx -> {
            final ProductRepository repo = new ProductRepository(tsx.dsl());
            Stream<ProductAPI> stream1 = repo.stream(repo::getFullQuery, fkQuery);
            Stream<List<ProductAPI>> chunkStream = chunk(stream1, 250);

            // the "parallel" is important here, as it really pushes performance.
            return chunkStream.parallel().flatMap(List::stream);
        });
    }

    public void exportJson(RequestContext requestContext, OutputStream os) {
        var productStream = streamAll(requestContext);

        try (JsonWriter<ProductAPI> jsonWriter = new JsonWriter<>(os, ProductAPI.class)) {
            final Iterator<ProductAPI> it = productStream.iterator();
            while (it.hasNext()) {
                ProductAPI product = it.next();
                jsonWriter.writeItem(product);
            }
        }
    }


    public void exportCsv(RequestContext requestContext, OutputStream os) {
        var productStream = streamAll(requestContext);

        ProductRecord pc = new ProductRecord();
        List<String> fieldNames = new ArrayList<>();
        for (Field<?> field : pc.fields()) {
            fieldNames.add(field.getName());
        }
        try (CsvWriter<ProductAPI> csvWriter = new CsvWriter<>(os, fieldNames)) {
            final Iterator<ProductAPI> it = productStream.iterator();
            while (it.hasNext()) {
                ProductAPI product = it.next();
                csvWriter.writeItem(product);
            }
        }
    }

    public void exportXlsx(RequestContext requestContext, OutputStream os) {
        var productStream = streamAll(requestContext);

        // Resolve fields that should be part of the export.
        ProductRecord pc = new ProductRecord();
        List<String> fieldNames = new ArrayList<>();
        for (Field<?> field : pc.fields()) {
            fieldNames.add(field.getName());
        }
        try (XlsxWriter<ProductAPI> xlsxWriter = new XlsxWriter<>(os, "Products", fieldNames)) {
            final Iterator<ProductAPI> it = productStream.iterator();
            while (it.hasNext()) {
                ProductAPI product = it.next();
                product.setTypeId(generateLorem());
                xlsxWriter.writeItem(product);
            }
        }
    }

    public void exportPdf(RequestContext requestContext, OutputStream os, String language, Integer chunkSize) {
        final Locale locale;
        if (language.equals("de")) {
            locale = Locale.GERMANY;
        } else {
            locale = Locale.US;
        }
        var productStream = streamAll(requestContext);
        Stream<List<ProductAPI>> chunkStream = chunk(productStream, chunkSize);

        ProductMessages productMessages = MessageBundles.get(ProductMessages.class, Localized.Literal.of(locale.toLanguageTag()));
        String test = productMessages.product_paginate_localizationTest();

        try (PdfWriter pdfWriter = new PdfWriter(os)) {
            // we need to create the target PDF
            // we'll create one page per input string, but we call layout for the first
            String introductionPage = "<html>" +
                "    Introduction..." +
                "</html>";
            pdfWriter.writeItem(introductionPage);

            // each page after the first we add using layout() followed by writeNextDocument()
            Iterator<List<ProductAPI>> it = chunkStream.iterator();
            while (it.hasNext()) {
                List<ProductAPI> productsChunk = it.next();

                List<ProductAPI> productsChunkWithData = productsChunk.stream().map(x -> (ProductAPI) x.setTypeId(generateLorem())).toList();

                String html = ProductTemplates.productsTemplate(productsChunkWithData).setLocale(locale).render();
                pdfWriter.writeItem(html);
            }
        }
    }

    private static String generateLorem() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return generatedString;
    }
}
