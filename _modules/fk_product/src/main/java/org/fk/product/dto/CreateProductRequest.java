package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(
    @NotNull Integer clientId,
    @NotNull BigDecimal price,
    @NotNull @Size(max = 255) String typeId
) {
}
