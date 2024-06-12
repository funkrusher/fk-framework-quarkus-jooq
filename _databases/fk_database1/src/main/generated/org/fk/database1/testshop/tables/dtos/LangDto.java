package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.ILang;

/**
 * Lang contains available languages of the app
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LangDto implements ILang, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer langId;
    private String code;
    private String description;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public LangDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public Integer LangId() {
        return this.langId;
    }

    /**
     * Setter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public Lang LangId(Integer langId) {
        this.langId = langId;
        return this;
    }

    /**
     * Getter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @NotNull
    @Size(max = 2)
    @Override
    public String Code() {
        return this.code;
    }

    /**
     * Setter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @Override
    public Lang Code(String code) {
        this.code = code;
        return this;
    }

    /**
     * Getter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Size(max = 50)
    @Override
    public String Description() {
        return this.description;
    }

    /**
     * Setter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Override
    public Lang Description(String description) {
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
    public void from(ILang from) {
        LangId(from.LangId());
        Code(from.Code());
        Description(from.Description());
    }
    @Override
    public <E extends ILang> E into(E into) {
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
