package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateProductResponse {
    @NotNull Long productId;
    @NotNull Integer clientId;
    @NotNull BigDecimal price;
    @NotNull @Size(max = 255) String typeId;
    @NotNull LocalDateTime createdAt;
    @NotNull LocalDateTime updatedAt;
    @NotNull Boolean deleted;
    @NotNull Integer creatorId;
}
