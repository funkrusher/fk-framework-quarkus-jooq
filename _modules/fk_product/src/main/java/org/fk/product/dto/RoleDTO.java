package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.interfaces.IRole;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.jooq.Record1;
import org.jooq.Record2;

import java.util.List;

/**
 * RoleDTO
 */
public class RoleDTO implements IRole, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    private String roleId;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public RoleDTO() {}

    public RoleDTO(IRole value) { this.from(value); }

    public static RoleDTO create(Record1<RoleRecord> r) {
        return new RoleDTO(r.value1());
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>testshop.role.roleId</code>.
     */
    @NotNull
    @Size(max = 50)
    @Override
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * Setter for <code>testshop.role.roleId</code>.
     */
    @Override
    public RoleDTO setRoleId(String roleId) {
        this.roleId = roleId;
        this.keeper.touch("roleId");
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
    public void from(IRole from) {
        setRoleId(from.getRoleId());
    }

    @Override
    public <E extends IRole> E into(E into) {
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