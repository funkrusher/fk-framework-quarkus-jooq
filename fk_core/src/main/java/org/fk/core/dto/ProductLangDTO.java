package org.fk.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.fk.codegen.testshop.tables.dtos.ProductLang;
import org.fk.codegen.testshop.tables.interfaces.IProductLang;

/**
 * ProductLangDTO
 */
@Valid
public class ProductLangDTO extends ProductLang implements IProductLang {

    private boolean insertFlag;
    private boolean deleteFlag;

    public ProductLangDTO() {
        super();
    }

    public ProductLangDTO(IProductLang value) {
        super(value);
    }

    @JsonProperty
    public void setInsertFlag(boolean insertFlag) {
        this.insertFlag = insertFlag;
        setAt("insertFlag", insertFlag);
    }

    @JsonIgnore
    public boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        setAt("deleteFlag", deleteFlag);
    }

    @JsonIgnore
    public boolean getDeleteFlag() {
        return deleteFlag;
    }
}
