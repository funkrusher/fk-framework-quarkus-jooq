package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop2.tables.records.ProductRecord;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@Schema(description = "Represents a request to create a new product.")
public class CreateProductRequest {

    @NotNull
    private Integer clientId;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Size(max = 255)
    private String typeId;

    // -------------------------------------------------------------------------
    // jOOQ Converters
    // -------------------------------------------------------------------------

    public ProductRecord jooq() {
        return new ProductRecord()
            .setClientId(getClientId())
            .setPrice(getPrice())
            .setTypeId(getTypeId());
    }

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
