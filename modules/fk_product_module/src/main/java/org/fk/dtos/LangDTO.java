package org.fk.dtos;


import jakarta.validation.Valid;
import org.fk.generated.testshop.tables.interfaces.ILang;
import org.fk.generated.testshop.tables.dtos.Lang;

/**
 * LangDTO
 */
@Valid
public class LangDTO extends Lang implements ILang {
}
