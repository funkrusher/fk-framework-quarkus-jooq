/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.dtos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.codegen.dto.AbstractDTO;
import org.fk.codegen.testshop.tables.interfaces.IUserRole;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Valid
@Entity
@Table(
    name = "user_role",
    schema = "testshop"
)
public class UserRole extends AbstractDTO implements IUserRole {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String roleId;

    public UserRole() {}

    public UserRole(IUserRole value) {
        this.userId = value.getUserId();
        this.roleId = value.getRoleId();
    }

    public UserRole(
        Integer userId,
        String roleId
    ) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * Getter for <code>testshop.user_role.userId</code>.
     */
    @Column(name = "userId", nullable = false)
    @NotNull
    @Override
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>testshop.user_role.userId</code>.
     */
    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
        this.setAt("userId", userId);
    }

    /**
     * Getter for <code>testshop.user_role.roleId</code>.
     */
    @Column(name = "roleId", nullable = false, length = 50)
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
    public void setRoleId(String roleId) {
        this.roleId = roleId;
        this.setAt("roleId", roleId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserRole other = (UserRole) obj;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.roleId == null) {
            if (other.roleId != null)
                return false;
        }
        else if (!this.roleId.equals(other.roleId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.roleId == null) ? 0 : this.roleId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UserRole (");

        sb.append(userId);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
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
}
