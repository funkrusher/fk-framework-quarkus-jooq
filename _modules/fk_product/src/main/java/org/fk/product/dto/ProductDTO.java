package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop2.tables.dtos.ProductDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.type.ProductTypeId;
import org.jooq.Record3;
import java.util.List;

/**
 * ProductDTO
 */
public class ProductDTO extends ProductDto<ProductDTO> {

    @Schema(writeOnly = true)
    private Boolean deleteFlag;

    @Schema(readOnly = true)
    private ProductLangDTO lang;

    private List<ProductLangDTO> langs;

    @Schema(readOnly = true)
    private ProductLangDTO mylang;

    @Schema(hidden = true)
    private ProductTypeId productTypeId;

    @Schema(hidden = true)
    private UserDTO creator;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductDTO() {}

    public ProductDTO(IProduct value) { this.from(value); }

    public static ProductDTO create(Record3<ProductRecord, UserDTO, List<ProductLangDTO>> r) {
        return new ProductDTO(r.value1())
            .setCreator(r.value2())
            .setLangs(r.value3());
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    @JsonIgnore
    public ProductDTO setCreator(UserDTO creator) {
        this.creator = creator;
        keeper.touch("creator");
        return this;
    }

    @JsonProperty
    public UserDTO getCreator() {
        return creator;
    }

    @JsonIgnore
    public ProductTypeId getProductTypeId() {
        return productTypeId;
    }

    @JsonIgnore
    public ProductDTO setProductTypeId(ProductTypeId productTypeId) {
        this.productTypeId = productTypeId;
        this.keeper.touch("productTypeId");
        return this;
    }

    @JsonProperty
    public ProductDTO setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
        return this;
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public ProductDTO setMylang(ProductLangDTO lang) {
        this.mylang = mylang;
        keeper.touch("mylang");
        return this;
    }

    @JsonProperty
    public ProductLangDTO getMylang() {
        return mylang;
    }


    @JsonIgnore
    public ProductDTO setLang(ProductLangDTO lang) {
        this.lang = lang;
        keeper.touch("lang");
        return this;
    }

    @JsonProperty
    public ProductLangDTO getLang() {
        return lang;
    }

    public List<ProductLangDTO> getLangs() {
        return langs;
    }

    public ProductDTO setLangs(List<ProductLangDTO> langs) {
        this.langs = langs;
        keeper.touch("langs");
        return this;
    }
}