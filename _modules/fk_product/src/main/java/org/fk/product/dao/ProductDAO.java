package org.fk.product.dao;

import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.core.dao.AbstractDAO;
import org.jooq.DSLContext;

/**
 * ProductRecordDAO
 */
public class ProductDAO extends AbstractDAO<ProductRecord, IProduct, Long> {

    public ProductDAO(DSLContext dsl) {
        super(dsl, Product.PRODUCT);
    }
}
