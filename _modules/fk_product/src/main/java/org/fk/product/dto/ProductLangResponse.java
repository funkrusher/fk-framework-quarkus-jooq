package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record2;

@Builder
public record ProductLangResponse(
    @NotNull Long productId,
    @NotNull Integer langId,
    @NotNull String name,
    @NotNull String description,
    @NotNull LangResponse lang
) {

    public static ProductLangResponse create(Record2<ProductLangRecord, LangResponse> rec) {
        ProductLangRecord lang = rec.value1();
        return ProductLangResponse.builder()
            .productId(lang.getProductid())
            .langId(lang.getLangid())
            .name(lang.getName())
            .description(lang.getDescription())
            .lang(rec.value2())
            .build();
    }

}