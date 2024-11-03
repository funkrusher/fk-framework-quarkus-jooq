package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Represents a request to create a new product.")
public class CreateProductRequest {
    @NotNull Integer clientId;
    @NotNull BigDecimal price;
    @NotNull @Size(max = 255) String typeId;

    // -------------------------------------------------------------------------
    // API Examples
    // -------------------------------------------------------------------------

    public static final String EXAMPLE1_NAME = "Example 1";
    public static final String EXAMPLE1_DESCRIPTION = "An example containing all required parameters";
    public static final String EXAMPLE1_VALUE = """
         {
           "test" : "test123"
         }
        """;
}
