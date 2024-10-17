package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Optional;

@Data
public class UpdateProductRequest {

    @NotNull
    Long productId;

    @NotNull
    Integer clientId;

    @Schema(required = false, nullable = false)
    Optional<BigDecimal> price = Optional.empty();

    @Schema(required = false, nullable = false)
    Optional<@NotNull @Size(max = 255) String> typeId = Optional.empty();
}
