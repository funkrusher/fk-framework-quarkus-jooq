package org.fk.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlTransient;

public class AbstractDTO {

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
        final AbstractDTO other = (AbstractDTO) obj;
        return this.keeper.touchedEquals(other);
    }

    @Override
    public int hashCode() {
        return this.keeper.touchedHashCode();
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
