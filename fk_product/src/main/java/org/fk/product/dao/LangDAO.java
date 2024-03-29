package org.fk.product.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database.testshop.tables.Lang;
import org.fk.database.testshop.tables.interfaces.ILang;
import org.fk.database.testshop.tables.records.LangRecord;
import org.jooq.DSLContext;

/**
 * LangRecordDAO
 */
public class LangDAO extends AbstractDAO<LangRecord, ILang, Integer> {

    public LangDAO(DSLContext dsl) {
        super(dsl, Lang.LANG);
    }

    @Override
    public Integer getId(LangRecord object) {
        return object.getLangId();
    }
}
