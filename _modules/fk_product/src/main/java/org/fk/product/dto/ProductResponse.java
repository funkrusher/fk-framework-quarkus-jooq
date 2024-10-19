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
public record ProductResponse(
    @NotNull Long productId,
    @NotNull Integer clientId,
    @NotNull BigDecimal price,
    @NotNull @Size(max = 255) String typeId,
    @NotNull LocalDateTime createdAt,
    @NotNull LocalDateTime updatedAt,
    @NotNull Boolean deleted,
    Integer creatorId,
    UserResponse creator,
    @NotNull List<ProductLangResponse> langs
) {
    public static ProductResponse create(Record3<ProductRecord, UserResponse, List<ProductLangResponse>> rec) {
        ProductRecord product = rec.value1();
        return ProductResponse.builder()
            .productId(product.getProductid())
            .clientId(product.getClientid())
            .price(product.getPrice())
            .typeId(product.getTypeid())
            .createdAt(product.getCreatedat())
            .updatedAt(product.getUpdatedat())
            .deleted(product.getDeleted())
            .creatorId(product.getCreatorid())
            .creator(rec.value2())
            .langs(rec.value3())
            .build();
    }
}