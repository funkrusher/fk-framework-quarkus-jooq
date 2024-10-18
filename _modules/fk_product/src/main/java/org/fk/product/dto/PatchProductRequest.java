package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

public record PatchProductRequest(

    @NotNull
    Long productId,

    @NotNull
    Integer clientId,

    @Schema(required = false, nullable = false)
    BigDecimal price,

    @Schema(required = false, nullable = false)
    @NotNull
    @Size(max = 255)
    String typeId
) {

}