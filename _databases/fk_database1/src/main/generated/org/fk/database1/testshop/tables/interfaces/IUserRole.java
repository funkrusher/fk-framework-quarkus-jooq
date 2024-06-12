/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IUserRole extends Serializable {

    /**
     * Setter for <code>testshop.user_role.userId</code>.
     */
    public IUserRole setUserId(Integer value);

    /**
     * Getter for <code>testshop.user_role.userId</code>.
     */
    @NotNull
    public Integer getUserId();

    /**
     * Setter for <code>testshop.user_role.roleId</code>.
     */
    public IUserRole setRoleId(String value);

    /**
     * Getter for <code>testshop.user_role.roleId</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getRoleId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IUserRole
     */
    public void from(IUserRole from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IUserRole
     */
    public <E extends IUserRole> E into(E into);
}
