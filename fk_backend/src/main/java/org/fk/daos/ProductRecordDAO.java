package org.fk.daos;

import org.fk.dao.AbstractRecordDAO;
import org.fk.generated.testshop.tables.Product;
import org.fk.generated.testshop.tables.interfaces.IProduct;
import org.fk.generated.testshop.tables.records.ProductRecord;
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
