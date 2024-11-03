package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.SelectField;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.*;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    @NotNull Long productId;
    @NotNull Integer clientId;
    @NotNull BigDecimal price;
    @NotNull @Size(max = 255) String typeId;
    @NotNull LocalDateTime createdAt;
    @NotNull LocalDateTime updatedAt;
    @NotNull Boolean deleted;
    Integer creatorId;
    UserResponse creator;
    @NotNull List<ProductLangResponse> langs;
}