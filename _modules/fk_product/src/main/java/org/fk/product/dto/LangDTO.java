package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database1.testshop.tables.interfaces.ILang;
import org.fk.database1.testshop.tables.pojos.LangDto;

/**
 * LangDTO
 */
public class LangDTO extends LangDto implements ILang {

    public LangDTO() {
        super();
    }

}
