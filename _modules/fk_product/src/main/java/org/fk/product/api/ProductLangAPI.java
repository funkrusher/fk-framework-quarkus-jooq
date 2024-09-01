package org.fk.product.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fk.database1.testshop2.tables.dtos.ProductLangDto;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record1;

/**
 * ProductLangDTO
 */
public class ProductLangAPI extends ProductLangDto {

    private Boolean insertFlag;
    private Boolean deleteFlag;
    private LangAPI lang;

    public ProductLangAPI() {}

    public ProductLangAPI(IProductLang value) { this.from(value); }

    public static ProductLangAPI create(Record1<ProductLangRecord> r) {
        return new ProductLangAPI(r.value1());
    }

    public void setLang(LangAPI lang) {
        this.lang = lang;
        keeper.touch("lang");
    }

    public LangAPI getLang() {
        return lang;
    }

    @JsonProperty
    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
        keeper.touch("insertFlag");
    }

    @JsonIgnore
    public Boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        keeper.touch("deleteFlag");
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }
}