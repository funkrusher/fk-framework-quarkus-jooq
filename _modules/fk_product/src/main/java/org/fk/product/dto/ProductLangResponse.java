package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductLangResponse {
    @NotNull Long productId;
    @NotNull Integer langId;
    @NotNull String name;
    @NotNull String description;
    @NotNull LangResponse lang;
}