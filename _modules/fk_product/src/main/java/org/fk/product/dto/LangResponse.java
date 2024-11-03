package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.Record1;
import org.jooq.SelectField;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.row;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LangResponse {
    @NotNull Integer langId;
    @NotNull String code;
    @NotNull String description;
}