package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductLangDTO(
    @NotNull Long productId,
    @NotNull Integer langId,
    @NotNull String name,
    @NotNull String description,
    @NotNull LangDTO lang
) {
}