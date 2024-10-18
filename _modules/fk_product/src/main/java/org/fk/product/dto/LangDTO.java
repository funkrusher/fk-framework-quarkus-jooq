package org.fk.product.dto;

import dev.mccue.magicbean.MagicBean;
import jakarta.validation.constraints.NotNull;

public record LangDTO (
    @NotNull
    Integer langId,
    @NotNull
    String code,
    @NotNull
    String description
){

}