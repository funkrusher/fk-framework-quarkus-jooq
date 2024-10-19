package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(
    description = "Represents a request to create a new product."
)
@Builder
public record CreateProductRequest(
    @NotNull Integer clientId,
    @NotNull BigDecimal price,
    @NotNull @Size(max = 255) String typeId
) {

    public static final String EXAMPLE1_NAME = "Example 1";
    public static final String EXAMPLE1_DESCRIPTION = "An example containing all required parameters";
    public static final String EXAMPLE1_VALUE = """
    {
      "test" : "test123
    }
   """;
}
