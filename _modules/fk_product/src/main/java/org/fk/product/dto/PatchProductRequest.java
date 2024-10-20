package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.FieldNameConstants;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@FieldNameConstants
public record PatchProductRequest(

    @Schema(required = true)
    @NotNull
    Long productId,

    @Schema(required = false)
    @NotNull
    Integer clientId,

    @Schema(required = false)
    BigDecimal price,

    @Schema(required = false)
    @NotNull
    @Size(max = 255)
    String typeId
) {
}