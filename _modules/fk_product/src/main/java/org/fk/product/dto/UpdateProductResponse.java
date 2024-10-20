package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record UpdateProductResponse(

    @NotNull
    Long productId,

    @NotNull
    Integer clientId,

    @NotNull
    BigDecimal price,

    @NotNull
    @Size(max = 255)
    String typeId,

    @NotNull
    LocalDateTime createdAt,

    @NotNull
    LocalDateTime updatedAt,

    @NotNull
    Boolean deleted,

    @NotNull
    Integer creatorId
) {
}
