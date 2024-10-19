package org.fk.product.dao;

import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.framework.dao.AbstractDAO;
import org.fk.database1.testshop2.tables.ProductLang;
import org.jooq.*;

/**
 * ProductLangDAO
 */
public class ProductLangDAO extends AbstractDAO<ProductLangRecord, Record2<Long, Integer>> {

    public ProductLangDAO(DSLContext dsl) {
        super(dsl, ProductLang.PRODUCT_LANG);
    }

    /**
     * Delete all by productId
     *
     * @param productId
     * @return delete count
     */
    public int deleteByProductId(Long productId) {
        return dsl().deleteFrom(table()).where(ProductLang.PRODUCT_LANG.PRODUCTID.eq(productId)).execute();
    }
}
