/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.codegen.testshop.tables.Databasechangeloglock;
import org.fk.codegen.testshop.tables.interfaces.IDatabasechangeloglock;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangeloglockRecord extends UpdatableRecordImpl<DatabasechangeloglockRecord> implements IDatabasechangeloglock {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    @Override
    public void setID(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    @NotNull
    @Override
    public Integer getID() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @Override
    public void setLOCKED(Byte value) {
        set(1, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @NotNull
    @Override
    public Byte getLOCKED() {
        return (Byte) get(1);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    @Override
    public void setLOCKGRANTED(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    @Override
    public LocalDateTime getLOCKGRANTED() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Override
    public void setLOCKEDBY(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Size(max = 255)
    @Override
    public String getLOCKEDBY() {
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
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IDatabasechangeloglock from) {
        setID(from.getID());
        setLOCKED(from.getLOCKED());
        setLOCKGRANTED(from.getLOCKGRANTED());
        setLOCKEDBY(from.getLOCKEDBY());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IDatabasechangeloglock> E into(E into) {
        into.from(this);
        return into;
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
    public DatabasechangeloglockRecord(Integer ID, Byte LOCKED, LocalDateTime LOCKGRANTED, String LOCKEDBY) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        setID(ID);
        setLOCKED(LOCKED);
        setLOCKGRANTED(LOCKGRANTED);
        setLOCKEDBY(LOCKEDBY);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DatabasechangeloglockRecord
     */
    public DatabasechangeloglockRecord(org.fk.codegen.testshop.tables.dtos.Databasechangeloglock value) {
        super(Databasechangeloglock.DATABASECHANGELOGLOCK);

        if (value != null) {
            setID(value.getID());
            setLOCKED(value.getLOCKED());
            setLOCKGRANTED(value.getLOCKGRANTED());
            setLOCKEDBY(value.getLOCKEDBY());
            resetChangedOnNotNull();
        }
    }
}
