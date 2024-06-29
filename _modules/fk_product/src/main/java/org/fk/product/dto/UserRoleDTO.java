package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.interfaces.IUserRole;

/**
 * UserRoleDTO
 */
public class UserRoleDTO implements IUserRole, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    private Integer userId;
    private String roleId;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public UserRoleDTO() {}

    public UserRoleDTO(IUserRole value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>testshop.user_role.userId</code>.
     */
    @NotNull
    @Override
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>testshop.user_role.userId</code>.
     */
    @Override
    public UserRoleDTO setUserId(Integer userId) {
        this.userId = userId;
        this.keeper.touch("userId");
        return this;
    }

    /**
     * Getter for <code>testshop.user_role.roleId</code>.
     */
    @NotNull
    @Size(max = 50)
    @Override
    public String getRoleId() {
        return this.roleId;
    }

    /**
     * Setter for <code>testshop.user_role.roleId</code>.
     */
    @Override
    public UserRoleDTO setRoleId(String roleId) {
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
    public void from(IUserRole from) {
        setUserId(from.getUserId());
        setRoleId(from.getRoleId());
    }

    @Override
    public <E extends IUserRole> E into(E into) {
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