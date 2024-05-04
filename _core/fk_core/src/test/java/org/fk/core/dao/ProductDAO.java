package org.fk.core.dao;

import org.fk.core.test.database.coretestdatabase.tables.Product;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IProduct;
import org.fk.core.test.database.coretestdatabase.tables.records.ProductRecord;
import org.jooq.DSLContext;

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
