package org.fk.product.dao;

import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.interfaces.IProductLang;
import org.fk.codegen.testshop.tables.records.ProductLangRecord;
import org.fk.core.dao.AbstractDAO;
import org.fk.codegen.testshop.tables.ProductLang;
import org.jooq.*;
import org.jooq.Record;

import java.util.List;
import java.util.Map;

import static org.jooq.impl.DSL.*;
import static org.jooq.impl.DSL.asterisk;

/**
 * ProductLangRecordDAO
 */
public class ProductLangDAO extends AbstractDAO<ProductLangRecord, IProductLang, Record2<Long, Integer>> {

    public ProductLangDAO(DSLContext dsl) {
        super(dsl, ProductLang.PRODUCT_LANG);
    }

    @Override
    public Record2<Long, Integer> getId(ProductLangRecord object) {
        return compositeKeyRecord(object.getProductId(), object.getLangId());
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
