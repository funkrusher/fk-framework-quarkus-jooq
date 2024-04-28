package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.IProductLang;
import org.fk.coreTestDatabase.coretestdatabase.tables.pojos.ProductLangDto;

/**
 * ProductLangDTO
 */
public class ProductLangDTO extends ProductLangDto implements IProductLang {

    private Boolean insertFlag;
    private Boolean deleteFlag;

    public ProductLangDTO() {
        super();
    }

    @JsonProperty
    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
        this.keeper.touch("insertFlag");
    }

    @JsonIgnore
    public Boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }
}
