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
public class NestedProductDTO extends ProductDto<NestedProductDTO> {

    @Schema(writeOnly = true)
    private Boolean deleteFlag;

    @Schema(readOnly = true)
    private NestedProductLangDTO lang;

    private List<NestedProductLangDTO> langs;

    @Schema(readOnly = true)
    private NestedProductLangDTO mylang;

    @Schema(hidden = true)
    private ProductTypeId productTypeId;

    @Schema(hidden = true)
    private NestedUserDTO creator;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public NestedProductDTO() {}

    public NestedProductDTO(IProduct value) { this.from(value); }

    public static NestedProductDTO create(Record3<ProductRecord, NestedUserDTO, List<NestedProductLangDTO>> r) {
        return new NestedProductDTO(r.value1())
            .setCreator(r.value2())
            .setLangs(r.value3());
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    @JsonIgnore
    public NestedProductDTO setCreator(NestedUserDTO creator) {
        this.creator = creator;
        keeper.touch("creator");
        return this;
    }

    @JsonProperty
    public NestedUserDTO getCreator() {
        return creator;
    }

    @JsonIgnore
    public ProductTypeId getProductTypeId() {
        return productTypeId;
    }

    @JsonIgnore
    public NestedProductDTO setProductTypeId(ProductTypeId productTypeId) {
        this.productTypeId = productTypeId;
        this.keeper.touch("productTypeId");
        return this;
    }

    @JsonProperty
    public NestedProductDTO setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
        return this;
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public NestedProductDTO setMylang(NestedProductLangDTO lang) {
        this.mylang = mylang;
        keeper.touch("mylang");
        return this;
    }

    @JsonProperty
    public NestedProductLangDTO getMylang() {
        return mylang;
    }


    @JsonIgnore
    public NestedProductDTO setLang(NestedProductLangDTO lang) {
        this.lang = lang;
        keeper.touch("lang");
        return this;
    }

    @JsonProperty
    public NestedProductLangDTO getLang() {
        return lang;
    }

    public List<NestedProductLangDTO> getLangs() {
        return langs;
    }

    public NestedProductDTO setLangs(List<NestedProductLangDTO> langs) {
        this.langs = langs;
        keeper.touch("langs");
        return this;
    }
}