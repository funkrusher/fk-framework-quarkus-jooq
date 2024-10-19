package org.fk.product.dao;

import org.fk.framework.dao.AbstractDAO;
import org.fk.database1.testshop.tables.Lang;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.DSLContext;

/**
 * LangRecordDAO
 */
public class LangDAO extends AbstractDAO<LangRecord, Integer> {

    public LangDAO(DSLContext dsl) {
        super(dsl, Lang.LANG);
    }
}
