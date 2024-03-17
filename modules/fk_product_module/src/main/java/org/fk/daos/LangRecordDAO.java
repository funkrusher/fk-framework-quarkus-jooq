package org.fk.daos;

import org.fk.dao.AbstractRecordDAO;
import org.fk.generated.testshop.tables.Lang;
import org.fk.generated.testshop.tables.interfaces.ILang;
import org.fk.generated.testshop.tables.records.LangRecord;
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
