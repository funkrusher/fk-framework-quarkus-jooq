package org.fk.daos.record;

import org.fk.daos.AbstractRecordDAO;
import org.fk.generated.jooq_testshop.tables.Product;
import org.fk.generated.jooq_testshop.tables.interfaces.IProduct;
import org.fk.generated.jooq_testshop.tables.records.ProductRecord;
import org.fk.jooq.JooqContext;

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
