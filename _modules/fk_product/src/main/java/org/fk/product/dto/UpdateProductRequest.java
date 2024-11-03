package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@FieldNameConstants
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductRequest {
    @Schema(required = true) @NotNull Long productId;
    @Schema(required = true) @NotNull Integer clientId;
    @Schema(required = true) BigDecimal price;
    @Schema(required = true) @NotNull @Size(max = 255) String typeId;
}