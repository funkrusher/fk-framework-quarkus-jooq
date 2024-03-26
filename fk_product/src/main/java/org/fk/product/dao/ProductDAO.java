package org.fk.product.dao;

import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.interfaces.IProduct;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

import java.util.List;

/**
 * ProductRecordDAO
 */
public class ProductDAO extends AbstractDAO<ProductRecord, IProduct, Long> {

    public ProductDAO(DSLContext dsl) {
        super(dsl, Product.PRODUCT);
    }

    @Override
    public Long getId(ProductRecord object) {
        return object.getProductId();
    }
}
