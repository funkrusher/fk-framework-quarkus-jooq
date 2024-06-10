package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.interfaces.ILang;

/**
 * LangDTO
 */
public class LangDTO implements ILang, DTO {

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

    public LangDTO() { this.keeper = new BookKeeper(this); }

    public LangDTO(
            // Database-Fields
            Integer langId,
            String code,
            String description
    ) {
        this.keeper = new BookKeeper(this);

        setLangId(langId);
        setCode(code);
        setDescription(description);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public Integer getLangId() {
        return this.langId;
    }

    /**
     * Setter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public LangDTO setLangId(Integer langId) {
        this.langId = langId;
        this.keeper.touch("langId");
        return this;
    }

    /**
     * Getter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @NotNull
    @Size(max = 2)
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @Override
    public LangDTO setCode(String code) {
        this.code = code;
        this.keeper.touch("code");
        return this;
    }

    /**
     * Getter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Size(max = 50)
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Override
    public LangDTO setDescription(String description) {
        this.description = description;
        this.keeper.touch("description");
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
        setLangId(from.getLangId());
        setCode(from.getCode());
        setDescription(from.getDescription());
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
