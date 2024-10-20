package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.FieldNameConstants;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@FieldNameConstants
public record UpdateProductRequest(

    @Schema(required = true)
    @NotNull
    Long productId,

    @Schema(required = true)
    @NotNull
    Integer clientId,

    @Schema(required = true)
    BigDecimal price,

    @Schema(required = true)
    @NotNull
    @Size(max = 255)
    String typeId
) {

}