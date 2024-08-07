/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IBasic1 extends Serializable {

    /**
     * Setter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    public IBasic1 setAutoIncId(Integer value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    public Integer getAutoIncId();

    /**
     * Setter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    public IBasic1 setString1(String value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    @Size(max = 50)
    public String getString1();

    /**
     * Setter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    public IBasic1 setString2(String value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    @Size(max = 50)
    public String getString2();

    /**
     * Setter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    public IBasic1 setInteger1(Integer value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    public Integer getInteger1();

    /**
     * Setter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    public IBasic1 setLong1(Long value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    public Long getLong1();

    /**
     * Setter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    public IBasic1 setDecimal1(BigDecimal value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    public BigDecimal getDecimal1();

    /**
     * Setter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    public IBasic1 setDateTime1(LocalDateTime value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    public LocalDateTime getDateTime1();

    /**
     * Setter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    public IBasic1 setClientId(Integer value);

    /**
     * Getter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    @NotNull
    public Integer getClientId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IBasic1
     */
    public void from(IBasic1 from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IBasic1
     */
    public <E extends IBasic1> E into(E into);
}
