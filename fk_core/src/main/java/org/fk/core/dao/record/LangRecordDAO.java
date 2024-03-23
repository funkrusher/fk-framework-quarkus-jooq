package org.fk.core.dao.record;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.Lang;
import org.fk.codegen.testshop.tables.interfaces.ILang;
import org.fk.codegen.testshop.tables.records.LangRecord;
import org.fk.core.jooq.JooqContext;

/**
 * LangRecordDAO
 */
public class LangRecordDAO extends AbstractRecordDAO<LangRecord, ILang, Integer> {

    public LangRecordDAO(JooqContext jooqContext) {
        super(jooqContext, Lang.LANG);
    }

    @Override
    public Integer getId(LangRecord object) {
        return object.getLangId();
    }
}
