package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop.tables.records.LangRecord;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record2;

@Builder
public record ProductLangDTO(
    @NotNull Long productId,
    @NotNull Integer langId,
    @NotNull String name,
    @NotNull String description,
    @NotNull LangDTO lang
) {

    public static ProductLangDTO create(Record2<ProductLangRecord, LangDTO> rec) {
        ProductLangRecord lang = rec.value1();
        return ProductLangDTO.builder()
            .productId(lang.getProductid())
            .langId(lang.getLangid())
            .name(lang.getName())
            .description(lang.getDescription())
            .lang(rec.value2())
            .build();
    }

}