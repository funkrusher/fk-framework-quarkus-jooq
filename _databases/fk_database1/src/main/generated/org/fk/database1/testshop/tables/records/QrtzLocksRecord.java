/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzLocks;
import org.fk.database1.testshop.tables.interfaces.IQrtzLocks;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzLocksRecord extends UpdatableRecordImpl<QrtzLocksRecord> implements IQrtzLocks {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_LOCKS.SCHED_NAME</code>.
     */
    @Override
    public QrtzLocksRecord setSCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_LOCKS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_LOCKS.LOCK_NAME</code>.
     */
    @Override
    public QrtzLocksRecord setLOCK_NAME(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_LOCKS.LOCK_NAME</code>.
     */
    @NotNull
    @Size(max = 40)
    @Override
    public String getLOCK_NAME() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzLocks from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setLOCK_NAME(from.getLOCK_NAME());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzLocks> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzLocksRecord
     */
    public QrtzLocksRecord() {
        super(QrtzLocks.QRTZ_LOCKS);
    }

    /**
     * Create a detached, initialised QrtzLocksRecord
     */
    public QrtzLocksRecord(String SCHED_NAME, String LOCK_NAME) {
        super(QrtzLocks.QRTZ_LOCKS);

        setSCHED_NAME(SCHED_NAME);
        setLOCK_NAME(LOCK_NAME);
        resetChangedOnNotNull();
    }
}
