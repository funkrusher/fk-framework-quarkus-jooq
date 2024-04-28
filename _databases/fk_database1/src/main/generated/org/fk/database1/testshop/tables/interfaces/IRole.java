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
public interface IRole extends Serializable {

    /**
     * Setter for <code>testshop.role.roleId</code>.
     */
    public IRole setRoleId(String value);

    /**
     * Getter for <code>testshop.role.roleId</code>.
     */
    @NotNull
    @Size(max = 50)
    public String getRoleId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IRole
     */
    public void from(IRole from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IRole
     */
    public <E extends IRole> E into(E into);
}
