/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fk.core.test.database.coretestdatabase.tables.Basic1;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Basic1Record extends UpdatableRecordImpl<Basic1Record> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    public Basic1Record setAutoincid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    public Integer getAutoincid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    public Basic1Record setString1(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    @Size(max = 50)
    public String getString1() {
        return (String) get(1);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    public Basic1Record setString2(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    @Size(max = 50)
    public String getString2() {
        return (String) get(2);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    public Basic1Record setInteger1(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    public Integer getInteger1() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    public Basic1Record setLong1(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    public Long getLong1() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    public Basic1Record setDecimal1(BigDecimal value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    public BigDecimal getDecimal1() {
        return (BigDecimal) get(5);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    public Basic1Record setDatetime1(LocalDateTime value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    public LocalDateTime getDatetime1() {
        return (LocalDateTime) get(6);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    public Basic1Record setClientid(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    @NotNull
    public Integer getClientid() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Basic1Record
     */
    public Basic1Record() {
        super(Basic1.BASIC1);
    }

    /**
     * Create a detached, initialised Basic1Record
     */
    public Basic1Record(Integer autoincid, String string1, String string2, Integer integer1, Long long1, BigDecimal decimal1, LocalDateTime datetime1, Integer clientid) {
        super(Basic1.BASIC1);

        setAutoincid(autoincid);
        setString1(string1);
        setString2(string2);
        setInteger1(integer1);
        setLong1(long1);
        setDecimal1(decimal1);
        setDatetime1(datetime1);
        setClientid(clientid);
        resetChangedOnNotNull();
    }
}
