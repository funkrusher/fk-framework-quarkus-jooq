package org.fk.daos.record;

import org.fk.daos.AbstractRecordDAO;
import org.fk.generated.jooq_testshop.tables.Lang;
import org.fk.generated.jooq_testshop.tables.interfaces.ILang;
import org.fk.generated.jooq_testshop.tables.records.LangRecord;
import org.fk.jooq.JooqContext;

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
