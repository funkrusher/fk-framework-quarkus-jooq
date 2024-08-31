package org.fk.product.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.type.ProductTypeId;
import org.jooq.Record3;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Client-specific Products
 */
public record ProductDTO (

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
    Long productId,
    Integer clientId,
    BigDecimal price,
    String typeId,
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    LocalDateTime createdAt,
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    LocalDateTime updatedAt,
    Boolean deleted,
    Integer creatorId,

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    List<ProductLangDTO> langs,

    @Schema(hidden = true)
    ProductTypeId productTypeId,

    @Schema(hidden = true)
    UserDTO creator
)  implements DTO {

    public static ProductDTO create(Record3<ProductRecord, UserDTO, List<ProductLangDTO>> r) {
        // Decompose the Record3 to its components
        ProductRecord productRecord = r.value1();
        UserDTO userDTO = r.value2();
        List<ProductLangDTO> productLangDTOs = r.value3();

        ProductTypeId productTypeId = ProductTypeId.fromValue(productRecord.getTypeId());

        // Return a new instance of ProductDTO
        return new ProductDTO(
            productRecord.getProductId(),
            productRecord.getClientId(),
            productRecord.getPrice(),
            productRecord.getTypeId(),
            productRecord.getCreatedAt(),
            productRecord.getUpdatedAt(),
            productRecord.getDeleted(),
            productRecord.getCreatorId(),
            productLangDTOs,
            productTypeId,
            userDTO
        );
    }
}