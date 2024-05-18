/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IBasic2 extends Serializable {

    /**
     * Setter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    public IBasic2 setUuidId(UUID value);

    /**
     * Getter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    @NotNull
    public UUID getUuidId();

    /**
     * Setter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    public IBasic2 setString1(String value);

    /**
     * Getter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    @Size(max = 50)
    public String getString1();

    /**
     * Setter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    public IBasic2 setString2(String value);

    /**
     * Getter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    @Size(max = 50)
    public String getString2();

    /**
     * Setter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    public IBasic2 setClientId(Integer value);

    /**
     * Getter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    @NotNull
    public Integer getClientId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IBasic2
     */
    public void from(IBasic2 from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IBasic2
     */
    public <E extends IBasic2> E into(E into);
}