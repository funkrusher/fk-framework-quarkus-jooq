package org.fk.core.dao;

import org.fk.core.test.database.coretestdatabase.tables.ProductLang;
import org.fk.core.test.database.coretestdatabase.tables.records.ProductLangRecord;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IProductLang;
import org.jooq.DSLContext;
import org.jooq.Record2;

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
}
