package org.fk.core.dto;


import jakarta.validation.Valid;
import org.fk.codegen.testshop.tables.dtos.Lang;
import org.fk.codegen.testshop.tables.interfaces.ILang;

/**
 * LangDTO
 */
@Valid
public class LangDTO extends Lang implements ILang {
}
