package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record3;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductDTO(
    @NotNull Long productId,
    @NotNull Integer clientId,
    @NotNull BigDecimal price,
    @NotNull @Size(max = 255) String typeId,
    @NotNull LocalDateTime createdAt,
    @NotNull LocalDateTime updatedAt,
    @NotNull Boolean deleted,
    Integer creatorId,
    UserDTO creator,
    @NotNull List<ProductLangDTO> langs
) {
    public static ProductDTO create(Record3<ProductRecord, UserDTO, List<ProductLangDTO>> rec) {
        ProductRecord product = rec.value1();
        return ProductDTO.builder()
            .productId(product.getProductId())
            .clientId(product.getClientId())
            .price(product.getPrice())
            .typeId(product.getTypeId())
            .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .deleted(product.getDeleted())
            .creatorId(product.getCreatorId())
            .creator(rec.value2())
            .langs(rec.value3())
            .build();
    }
}