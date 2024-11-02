package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.Record1;
import org.jooq.SelectField;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.row;

@Builder
public record LangResponse(
    @NotNull
    Integer langId,
    @NotNull
    String code,
    @NotNull
    String description
) {

    public static LangResponse create(Record1<LangRecord> rec) {
        LangRecord lang = rec.value1();
        return LangResponse.builder()
            .langId(lang.getLangId())
            .code(lang.getCode())
            .description(lang.getDescription())
            .build();
    }


    public static SelectField<LangResponse> langSelector() {
        return row(
            PRODUCT.productLang().lang()
        ).convertFrom(LangResponse::create);
    }

}