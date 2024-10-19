package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.jooq.Record1;

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
            .langId(lang.getLangid())
            .code(lang.getCode())
            .description(lang.getDescription())
            .build();
    }

}