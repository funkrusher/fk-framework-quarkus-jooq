package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Field;
import org.jooq.Record2;

import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.*;

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
            .productId(lang.getProductId())
            .langId(lang.getLangId())
            .name(lang.getName())
            .description(lang.getDescription())
            .lang(rec.value2())
            .build();
    }

    public static Field<List<ProductLangResponse>> productLangsSelector() {
        return multiset(
            select(
                PRODUCT.productLang(),
                LangResponse.langSelector()
            ).from(PRODUCT.productLang())
        ).convertFrom(r -> r.map(ProductLangResponse::create));
    }
}