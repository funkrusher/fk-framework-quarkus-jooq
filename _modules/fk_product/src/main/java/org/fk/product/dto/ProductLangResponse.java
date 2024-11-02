package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record2;

@Data
@Accessors(chain = true)
public class ProductLangResponse {

    @NotNull
    Long productId;

    @NotNull
    Integer langId;

    @NotNull
    String name;

    @NotNull
    String description;

    @NotNull
    LangResponse lang;

    // -------------------------------------------------------------------------
    // jOOQ Converters
    // -------------------------------------------------------------------------

    public static ProductLangResponse from(ProductLangRecord from) {
        return new ProductLangResponse()
            .setProductId(from.getProductId())
            .setLangId(from.getLangId())
            .setName(from.getName())
            .setDescription(from.getDescription());
    }

    public static ProductLangResponse convertFrom(Record2<ProductLangRecord, LangResponse> rec) {
        return ProductLangResponse.from(rec.value1())
            .setLang(rec.value2());
    }
}