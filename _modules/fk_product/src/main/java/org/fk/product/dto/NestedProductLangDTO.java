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
public class NestedProductLangDTO extends ProductLangDto<NestedProductLangDTO> {

    private Boolean insertFlag;
    private Boolean deleteFlag;
    private LangDTO lang;

    public NestedProductLangDTO() {}

    public NestedProductLangDTO(IProductLang value) { this.from(value); }

    public static NestedProductLangDTO create(Record1<ProductLangRecord> r) {
        return new NestedProductLangDTO(r.value1());
    }

    public void setLang(LangDTO lang) {
        this.lang = lang;
    }

    public LangDTO getLang() {
        return lang;
    }

    @JsonProperty
    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
    }

    @JsonIgnore
    public Boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }
}