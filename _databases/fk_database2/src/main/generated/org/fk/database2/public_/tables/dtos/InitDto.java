package org.fk.database2.public_.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database2.public_.tables.interfaces.IInit;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class InitDto implements IInit, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String initialized;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public InitDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>public.init.initialized</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getInitialized() {
        return this.initialized;
    }

    /**
     * Setter for <code>public.init.initialized</code>.
     */
    @Override
    public InitDto setInitialized(String initialized) {
        this.initialized = initialized;
        this.keeper.touch("initialized");
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
    public void from(IInit from) {
        setInitialized(from.getInitialized());
    }
    @Override
    public <E extends IInit> E into(E into) {
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
