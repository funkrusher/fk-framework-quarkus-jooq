package org.fk.core.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.SchemaProperty;

import org.fk.coreTestDatabase.coretestdatabase.tables.dtos.Product;
import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.IProduct;

import java.util.List;

/**
 * ProductDTO
 */
@Schema(name = "Product", description = "Represents a product",
properties = {
        @SchemaProperty(name = "createdAt", example = "1618312800000", type = SchemaType.STRING, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z"),
        @SchemaProperty(name = "updatedAt", example = "1618312800000", type = SchemaType.STRING, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
})
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
        touch();
    }

    @JsonProperty
    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public void setLang(ProductLangDTO lang) {
        this.lang = lang;
        touch();
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
        touch();
    }
}
