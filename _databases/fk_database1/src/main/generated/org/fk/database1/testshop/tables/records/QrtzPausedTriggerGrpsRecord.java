/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzPausedTriggerGrps;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzPausedTriggerGrpsRecord extends UpdatableRecordImpl<QrtzPausedTriggerGrpsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.SCHED_NAME</code>.
     */
    public QrtzPausedTriggerGrpsRecord setSchedName(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    public String getSchedName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.TRIGGER_GROUP</code>.
     */
    public QrtzPausedTriggerGrpsRecord setTriggerGroup(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getTriggerGroup() {
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
    public QrtzPausedTriggerGrpsRecord(String schedName, String triggerGroup) {
        super(QrtzPausedTriggerGrps.QRTZ_PAUSED_TRIGGER_GRPS);

        setSchedName(schedName);
        setTriggerGroup(triggerGroup);
        resetChangedOnNotNull();
    }
}
