package org.fk.product.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.codegen.testshop.tables.Lang;
import org.fk.codegen.testshop.tables.interfaces.ILang;
import org.fk.codegen.testshop.tables.records.LangRecord;
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
