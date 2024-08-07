package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.jooq.Record1;

import java.io.Serial;

/**
 * ProductLangDTO
 */
public class ProductLangDTO implements IProductLang, DTO {

    @Serial
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    private Long productId;
    private Integer langId;
    private String name;
    private String description;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
    private Boolean insertFlag;
    private Boolean deleteFlag;
    private LangDTO lang;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductLangDTO() {}

    public ProductLangDTO(IProductLang value) { this.from(value); }

    public static ProductLangDTO create(Record1<ProductLangRecord> r) {
        return new ProductLangDTO(r.value1());
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>testshop2.product_lang.productId</code>.
     */
    @NotNull
    @Override
    public Long getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>testshop2.product_lang.productId</code>.
     */
    @Override
    public ProductLangDTO setProductId(Long productId) {
        this.productId = productId;
        this.keeper.touch("productId");
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.langId</code>.
     */
    @NotNull
    @Override
    public Integer getLangId() {
        return this.langId;
    }

    /**
     * Setter for <code>testshop2.product_lang.langId</code>.
     */
    @Override
    public ProductLangDTO setLangId(Integer langId) {
        this.langId = langId;
        this.keeper.touch("langId");
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.name</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>testshop2.product_lang.name</code>.
     */
    @Override
    public ProductLangDTO setName(String name) {
        this.name = name;
        this.keeper.touch("name");
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.description</code>.
     */
    @NotNull
    @Size(max = 65535)
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>testshop2.product_lang.description</code>.
     */
    @Override
    public ProductLangDTO setDescription(String description) {
        this.description = description;
        this.keeper.touch("description");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    public void setLang(LangDTO lang) {
        this.lang = lang;
        keeper.touch("lang");
    }

    public LangDTO getLang() {
        return lang;
    }

    @JsonProperty
    public void setInsertFlag(Boolean insertFlag) {
        this.insertFlag = insertFlag;
        keeper.touch("insertFlag");
    }

    @JsonIgnore
    public Boolean getInsertFlag() {
        return insertFlag;
    }

    @JsonProperty
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        keeper.touch("deleteFlag");
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    // -------------------------------------------------------------------------
    // ToString, Equals, HashCode
    // -------------------------------------------------------------------------

    @Override
    public String toString() {
        return keeper.touchedToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DTO other = (DTO) obj;
        return this.keeper.touchedEquals(other);
    }

    @Override
    public int hashCode() {
        return this.keeper.touchedHashCode();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IProductLang from) {
        setProductId(from.getProductId());
        setLangId(from.getLangId());
        setName(from.getName());
        setDescription(from.getDescription());
    }

    @Override
    public <E extends IProductLang> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------

    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper = new BookKeeper(this);

    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}