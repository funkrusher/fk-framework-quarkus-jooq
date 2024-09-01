package org.fk.product.api;

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
 * Client-specific Products
 */
public class ProductAPI extends ProductDto {

    @Schema(writeOnly = true)
    private Boolean deleteFlag;

    @Schema(readOnly = true)
    private ProductLangAPI lang;

    private List<ProductLangAPI> langs;

    @Schema(readOnly = true)
    private ProductLangAPI mylang;

    @Schema(hidden = true)
    private ProductTypeId productTypeId;

    @Schema(hidden = true)
    private UserAPI creator;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductAPI() {}

    public ProductAPI(IProduct value) { this.from(value); }

    public static ProductAPI create(Record3<ProductRecord, UserAPI, List<ProductLangAPI>> r) {
        return new ProductAPI(r.value1())
            .setCreator(r.value2())
            .setLangs(r.value3());
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    @JsonIgnore
    public ProductAPI setCreator(UserAPI creator) {
        this.creator = creator;
        keeper.touch("creator");
        return this;
    }

    @JsonProperty
    public UserAPI getCreator() {
        return creator;
    }

    @JsonIgnore
    public ProductTypeId getProductTypeId() {
        return productTypeId;
    }

    @JsonIgnore
    public ProductAPI setProductTypeId(ProductTypeId productTypeId) {
        this.productTypeId = productTypeId;
        this.keeper.touch("productTypeId");
        return this;
    }

    @JsonProperty
    public ProductAPI setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
        return this;
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public ProductAPI setMylang(ProductLangAPI lang) {
        this.mylang = mylang;
        keeper.touch("mylang");
        return this;
    }

    @JsonProperty
    public ProductLangAPI getMylang() {
        return mylang;
    }


    @JsonIgnore
    public ProductAPI setLang(ProductLangAPI lang) {
        this.lang = lang;
        keeper.touch("lang");
        return this;
    }

    @JsonProperty
    public ProductLangAPI getLang() {
        return lang;
    }

    public List<ProductLangAPI> getLangs() {
        return langs;
    }

    public ProductAPI setLangs(List<ProductLangAPI> langs) {
        this.langs = langs;
        keeper.touch("langs");
        return this;
    }
}