package org.fk.product.dao;

import org.fk.database.testshop.tables.Product;
import org.fk.database.testshop.tables.ProductLang;
import org.fk.database.testshop.tables.interfaces.IProduct;
import org.fk.database.testshop.tables.records.ProductRecord;
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
