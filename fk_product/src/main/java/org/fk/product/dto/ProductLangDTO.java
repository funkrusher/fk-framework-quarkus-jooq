package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.fk.database.testshop.tables.dtos.ProductLang;
import org.fk.database.testshop.tables.interfaces.IProductLang;

/**
 * ProductLangDTO
 */
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
        touch();
    }

    @JsonIgnore
    public boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        touch();
    }

    @JsonIgnore
    public boolean getDeleteFlag() {
        return deleteFlag;
    }
}
