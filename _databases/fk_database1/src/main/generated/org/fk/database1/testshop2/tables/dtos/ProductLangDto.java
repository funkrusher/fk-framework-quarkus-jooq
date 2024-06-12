package org.fk.database1.testshop2.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop2.tables.interfaces.IProductLang;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProductLangDto implements IProductLang, DTO {

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
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public ProductLangDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop2.product_lang.productId</code>.
     */
    @NotNull
    @Override
    public Long ProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>testshop2.product_lang.productId</code>.
     */
    @Override
    public ProductLang ProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.langId</code>.
     */
    @NotNull
    @Override
    public Integer LangId() {
        return this.langId;
    }

    /**
     * Setter for <code>testshop2.product_lang.langId</code>.
     */
    @Override
    public ProductLang LangId(Integer langId) {
        this.langId = langId;
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.name</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String Name() {
        return this.name;
    }

    /**
     * Setter for <code>testshop2.product_lang.name</code>.
     */
    @Override
    public ProductLang Name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.description</code>.
     */
    @NotNull
    @Size(max = 65535)
    @Override
    public String Description() {
        return this.description;
    }

    /**
     * Setter for <code>testshop2.product_lang.description</code>.
     */
    @Override
    public ProductLang Description(String description) {
        this.description = description;
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------
 
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
        ProductId(from.ProductId());
        LangId(from.LangId());
        Name(from.Name());
        Description(from.Description());
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
    protected transient BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
