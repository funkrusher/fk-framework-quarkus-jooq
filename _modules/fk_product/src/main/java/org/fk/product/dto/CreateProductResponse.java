package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateProductResponse(
    @NotNull Long productId,
    @NotNull Integer clientId,
    @NotNull BigDecimal price,
    @NotNull @Size(max = 255) String typeId,
    @NotNull LocalDateTime createdAt,
    @NotNull LocalDateTime updatedAt,
    @NotNull Boolean deleted,
    @NotNull Integer creatorId
) {
}