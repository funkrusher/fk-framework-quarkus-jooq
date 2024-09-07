package org.fk.product.dto;

import org.fk.database1.testshop.tables.dtos.LangDto;
import org.fk.database1.testshop.tables.interfaces.ILang;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.Record1;

/**
 * LangDTO
 */
public class LangDTO extends LangDto<LangDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public LangDTO() {
        super();
    }

    public LangDTO(ILang value) {
        super(value);
    }

    public static LangDTO create(Record1<LangRecord> r) {
        return new LangDTO(r.value1());
    }

}
