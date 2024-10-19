package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CreateProductResponse(
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

    public static final String EXAMPLE1_NAME = "Example 1";
    public static final String EXAMPLE1_DESCRIPTION = "An example containing all required parameters";
    public static final String EXAMPLE1_VALUE = """
    {
      "test" : "test123
    }
   """;
}