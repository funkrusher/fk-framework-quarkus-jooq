package org.fk.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.codegen.testshop.tables.dtos.Product;
import org.fk.codegen.testshop.tables.interfaces.IProduct;

import java.util.List;

/**
 * ProductDTO
 */
@Schema(name = "Product", description = "Represents a product")
public class ProductDTO extends Product implements IProduct {

    @Schema(writeOnly = true)
    private boolean deleteFlag;

    @Schema(readOnly = true)
    private ProductLangDTO lang;

    private List<ProductLangDTO> langs;

    public ProductDTO() {
        super();
    }

    public ProductDTO(IProduct value) {
        super(value);
    }

    @JsonIgnore
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        setAt("deleteFlag", deleteFlag);
    }

    @JsonProperty
    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public void setLang(ProductLangDTO lang) {
        this.lang = lang;
        setAt("lang", lang);
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
        setAt("langs", langs);
    }
}
