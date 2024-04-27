/*
 * This file is generated by jOOQ.
 */
package org.fk.coreTestDatabase.coretestdatabase.tables.pojos;
import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.ILang;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LangDto implements ILang, DTO {

    private static final long serialVersionUID = 1L;

    private Integer langId;
    private String code;
    private String description;

    public LangDto() { this.keeper = new BookKeeper(this); }


    /**
     * Getter for <code>coreTestDatabase.lang.langId</code>.
     */
    @Override
    public Integer getLangId() {
        return this.langId;
    }

    /**
     * Setter for <code>coreTestDatabase.lang.langId</code>.
     */
    @Override
    public LangDto setLangId(Integer langId) {
        this.langId = langId;
        this.keeper.touch("langId");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.lang.code</code>.
     */
    @NotNull
    @Size(max = 2)
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>coreTestDatabase.lang.code</code>.
     */
    @Override
    public LangDto setCode(String code) {
        this.code = code;
        this.keeper.touch("code");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.lang.description</code>.
     */
    @Size(max = 50)
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>coreTestDatabase.lang.description</code>.
     */
    @Override
    public LangDto setDescription(String description) {
        this.description = description;
        this.keeper.touch("description");
        return this;
    }

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
    protected BookKeeper keeper;
    
    @JsonIgnore
    @XmlTransient
    @Override
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
