package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import org.fk.core.test.database.coretestdatabase.tables.interfaces.IProduct;
import org.fk.core.test.database.coretestdatabase.tables.dtos.ProductDto;

import java.util.List;

/**
 * ProductDTO
 */
public class ProductDTO extends ProductDto implements IProduct {

    @Schema(writeOnly = true)
    private Boolean deleteFlag;

    @Schema(readOnly = true)
    private ProductLangDTO lang;

    private List<ProductLangDTO> langs;

    public ProductDTO() {
        super();
    }


    @JsonIgnore
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
    }

    @JsonProperty
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public void setLang(ProductLangDTO lang) {
        this.lang = lang;
        this.keeper.touch("lang");
    }

    @JsonProperty
    public ProductLangDTO getLang() {
        return lang;
    }

    public List<ProductLangDTO> getLangs() {
        return langs;
    }

    public void setLangs(List<ProductLangDTO> langs) {
        this.langs = langs;
        this.keeper.touch("langs");
    }
}
