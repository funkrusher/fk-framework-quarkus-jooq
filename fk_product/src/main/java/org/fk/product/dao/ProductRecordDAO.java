package org.fk.product.dao;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.interfaces.IProduct;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.jooq.DSLContext;

/**
 * ProductRecordDAO
 */
public class ProductRecordDAO extends AbstractRecordDAO<ProductRecord, IProduct, Long> {

    public ProductRecordDAO(DSLContext dsl) {
        super(dsl, Product.PRODUCT);
    }

    @Override
    public Long getId(ProductRecord object) {
        return object.getProductId();
    }
}
