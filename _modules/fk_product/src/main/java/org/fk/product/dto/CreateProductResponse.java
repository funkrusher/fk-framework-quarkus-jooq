package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateProductResponse {
    @NotNull
    private Long productId;

    @NotNull
    private Integer clientId;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(max = 255)
    private String typeId;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;

    @NotNull
    private Boolean deleted;

    @NotNull
    private Integer creatorId;
}