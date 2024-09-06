package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fk.database1.testshop2.tables.dtos.ProductLangDto;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record1;

/**
 * ProductLangDTO
 */
public class ProductLangDTO extends ProductLangDto<ProductLangDTO> {

    private Boolean insertFlag;
    private Boolean deleteFlag;
    private LangDTO lang;

    public ProductLangDTO() {}

    public ProductLangDTO(IProductLang value) { this.from(value); }

    public static ProductLangDTO create(Record1<ProductLangRecord> r) {
        return new ProductLangDTO(r.value1());
    }

    public void setLang(LangDTO lang) {
        this.lang = lang;
        keeper.touch("lang");
    }

    public LangDTO getLang() {
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