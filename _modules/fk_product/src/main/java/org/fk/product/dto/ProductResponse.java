package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.SelectField;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.*;

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