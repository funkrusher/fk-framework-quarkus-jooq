package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.interfaces.ILang;

import java.io.Serial;

/**
 * LangDTO
 */
public record LangDTO(
    Integer langId,
    String code,
    String description
) implements DTO {

}
