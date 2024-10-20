/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.Databasechangeloglock;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangeloglockRecord extends UpdatableRecordImpl<DatabasechangeloglockRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    public DatabasechangeloglockRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    @NotNull
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public DatabasechangeloglockRecord setLocked(Boolean value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @NotNull
    public Boolean getLocked() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public DatabasechangeloglockRecord setLockgranted(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public LocalDateTime getLockgranted() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public DatabasechangeloglockRecord setLockedby(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Size(max = 255)
    public String getLockedby() {
        return (String) get(3);
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
     * Create a detached DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord() {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);
    }

    /**
     * Create a detached, initialised DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord(Integer id, Boolean locked, LocalDateTime lockgranted, String lockedby) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        setId(id);
        setLocked(locked);
        setLockgranted(lockgranted);
        setLockedby(lockedby);
        resetChangedOnNotNull();
    }
}
