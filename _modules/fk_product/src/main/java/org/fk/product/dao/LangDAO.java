package org.fk.product.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop.tables.interfaces.ILang;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.DSLContext;

/**
 * LangRecordDAO
 */
public class LangDAO extends AbstractDAO<LangRecord, ILang, Integer> {

    public LangDAO(DSLContext dsl) {
        super(dsl, Lang.LANG);
    }
}
