/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzPausedTriggerGrps;
import org.fk.database1.testshop.tables.interfaces.IQrtzPausedTriggerGrps;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzPausedTriggerGrpsRecord extends UpdatableRecordImpl<QrtzPausedTriggerGrpsRecord> implements IQrtzPausedTriggerGrps {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.SCHED_NAME</code>.
     */
    @Override
    public QrtzPausedTriggerGrpsRecord SCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String SCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzPausedTriggerGrpsRecord TRIGGER_GROUP(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String TRIGGER_GROUP() {
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
    public void from(IQrtzPausedTriggerGrps from) {
        SCHED_NAME(from.SCHED_NAME());
        TRIGGER_GROUP(from.TRIGGER_GROUP());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzPausedTriggerGrps> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzPausedTriggerGrpsRecord
     */
    public QrtzPausedTriggerGrpsRecord() {
        super(QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS);
    }

    /**
     * Create a detached, initialised QrtzPausedTriggerGrpsRecord
     */
    public QrtzPausedTriggerGrpsRecord(String SCHED_NAME, String TRIGGER_GROUP) {
        super(QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS);

        SCHED_NAME(SCHED_NAME);
        TRIGGER_GROUP(TRIGGER_GROUP);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzPausedTriggerGrpsRecord
     */
    public QrtzPausedTriggerGrpsRecord(org.fk.database1.testshop.tables.pojos.QrtzPausedTriggerGrps value) {
        super(QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS);

        if (value != null) {
            SCHED_NAME(value.SCHED_NAME());
            TRIGGER_GROUP(value.TRIGGER_GROUP());
            resetChangedOnNotNull();
        }
    }
}
