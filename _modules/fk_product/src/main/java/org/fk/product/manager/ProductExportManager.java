package org.fk.product.manager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.ValidationException;
import org.fk.core.manager.AbstractManager;
import org.fk.core.query.model.FkFilter;
import org.fk.core.query.model.FkFilterOperator;
import org.fk.core.query.model.FkQuery;
import org.fk.core.request.RequestContext;
import org.fk.core.transfer.xlsx.XlsxWriter;
import org.fk.database1.Database1;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dao.ProductDAO;
import org.fk.product.dao.ProductLangDAO;
import org.fk.product.dto.ProductDTO;
import org.fk.product.dto.ProductLangDTO;
import org.fk.product.dto.ProductPaginateDTO;
import org.fk.product.repository.ProductRepository;
import org.jboss.logging.Logger;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.exception.DataAccessException;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.jooq.impl.DSL.*;

/**
 * ProductExportManager
 */
@ApplicationScoped
public class ProductExportManager extends AbstractManager {

    private static final Logger LOGGER = Logger.getLogger(ProductExportManager.class);

    @Inject
    ProductManager productManager;

    @Inject
    Database1 database1;

    public void exportXlsx(RequestContext requestContext, OutputStream os) {
        DSLContext dsl = database1.dsl(requestContext);
        var productStream = productManager.streamAll(dsl);

        // Resolve fields that should be part of the export.
        ProductRecord pc = new ProductRecord();
        List<String> fieldNames = new ArrayList<>();
        for (Field<?> field : pc.fields()) {
            fieldNames.add(field.getName());
        }

        try (XlsxWriter<ProductDTO> xlsxWriter = new XlsxWriter<>(os, "Products", fieldNames)) {
            final Iterator<ProductDTO> it = productStream.iterator();
            while (it.hasNext()) {
                ProductDTO product = it.next();
                product.setTypeId(generateLorem());
                xlsxWriter.writeItem(product);
            }
        }
    }


    private String generateLorem() {
        String generatedString = RandomStringUtils.randomAlphanumeric(100);
        return generatedString;
    }
}
