package org.fk.core.dao.record;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.interfaces.IProduct;
import org.fk.codegen.testshop.tables.records.ProductRecord;
import org.fk.core.jooq.JooqContext;

/**
 * ProductRecordDAO
 */
public class ProductRecordDAO extends AbstractRecordDAO<ProductRecord, IProduct, Long> {

    public ProductRecordDAO(JooqContext jooqContext) {
        super(jooqContext, Product.PRODUCT);
    }

    @Override
    public Long getId(ProductRecord object) {
        return object.getProductId();
    }
}
