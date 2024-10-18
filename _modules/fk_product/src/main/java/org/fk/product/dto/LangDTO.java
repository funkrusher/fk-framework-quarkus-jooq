package org.fk.product.dto;

import dev.mccue.magicbean.MagicBean;
import jakarta.validation.constraints.NotNull;

@MagicBean
public final class LangDTO extends LangDTOBeanOps {
    @NotNull
    Integer langId;
    @NotNull
    String code;
    @NotNull
    String description;
}