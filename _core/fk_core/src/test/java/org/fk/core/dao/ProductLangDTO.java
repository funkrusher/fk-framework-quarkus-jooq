package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.IProductLang;
import org.fk.coreTestDatabase.coretestdatabase.tables.pojos.ProductLangDto;

/**
 * ProductLangDTO
 */
public class ProductLangDTO extends ProductLangDto implements IProductLang {

    private boolean insertFlag;
    private boolean deleteFlag;

    public ProductLangDTO() {
        super();
    }

    @JsonProperty
    public void setInsertFlag(boolean insertFlag) {
        this.insertFlag = insertFlag;
        this.keeper.touch("insertFlag");
    }

    @JsonIgnore
    public boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
    }

    @JsonIgnore
    public boolean getDeleteFlag() {
        return deleteFlag;
    }
}
