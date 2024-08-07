/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzFiredTriggers;
import org.fk.database1.testshop.tables.interfaces.IQrtzFiredTriggers;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzFiredTriggersRecord extends UpdatableRecordImpl<QrtzFiredTriggersRecord> implements IQrtzFiredTriggers {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setSCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setENTRY_ID(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    @NotNull
    @Size(max = 95)
    @Override
    public String getENTRY_ID() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setTRIGGER_NAME(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setTRIGGER_GROUP(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return (String) get(3);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setINSTANCE_NAME(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getINSTANCE_NAME() {
        return (String) get(4);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setFIRED_TIME(Long value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    @NotNull
    @Override
    public Long getFIRED_TIME() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setSCHED_TIME(Long value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    @NotNull
    @Override
    public Long getSCHED_TIME() {
        return (Long) get(6);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setPRIORITY(Integer value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    @NotNull
    @Override
    public Integer getPRIORITY() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setSTATE(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    @NotNull
    @Size(max = 16)
    @Override
    public String getSTATE() {
        return (String) get(8);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setJOB_NAME(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    @Size(max = 190)
    @Override
    public String getJOB_NAME() {
        return (String) get(9);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setJOB_GROUP(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    @Size(max = 190)
    @Override
    public String getJOB_GROUP() {
        return (String) get(10);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setIS_NONCONCURRENT(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    @Size(max = 1)
    @Override
    public String getIS_NONCONCURRENT() {
        return (String) get(11);
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    @Override
    public QrtzFiredTriggersRecord setREQUESTS_RECOVERY(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    @Size(max = 1)
    @Override
    public String getREQUESTS_RECOVERY() {
        return (String) get(12);
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
    public void from(IQrtzFiredTriggers from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setENTRY_ID(from.getENTRY_ID());
        setTRIGGER_NAME(from.getTRIGGER_NAME());
        setTRIGGER_GROUP(from.getTRIGGER_GROUP());
        setINSTANCE_NAME(from.getINSTANCE_NAME());
        setFIRED_TIME(from.getFIRED_TIME());
        setSCHED_TIME(from.getSCHED_TIME());
        setPRIORITY(from.getPRIORITY());
        setSTATE(from.getSTATE());
        setJOB_NAME(from.getJOB_NAME());
        setJOB_GROUP(from.getJOB_GROUP());
        setIS_NONCONCURRENT(from.getIS_NONCONCURRENT());
        setREQUESTS_RECOVERY(from.getREQUESTS_RECOVERY());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzFiredTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzFiredTriggersRecord
     */
    public QrtzFiredTriggersRecord() {
        super(QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS);
    }

    /**
     * Create a detached, initialised QrtzFiredTriggersRecord
     */
    public QrtzFiredTriggersRecord(String SCHED_NAME, String ENTRY_ID, String TRIGGER_NAME, String TRIGGER_GROUP, String INSTANCE_NAME, Long FIRED_TIME, Long SCHED_TIME, Integer PRIORITY, String STATE, String JOB_NAME, String JOB_GROUP, String IS_NONCONCURRENT, String REQUESTS_RECOVERY) {
        super(QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS);

        setSCHED_NAME(SCHED_NAME);
        setENTRY_ID(ENTRY_ID);
        setTRIGGER_NAME(TRIGGER_NAME);
        setTRIGGER_GROUP(TRIGGER_GROUP);
        setINSTANCE_NAME(INSTANCE_NAME);
        setFIRED_TIME(FIRED_TIME);
        setSCHED_TIME(SCHED_TIME);
        setPRIORITY(PRIORITY);
        setSTATE(STATE);
        setJOB_NAME(JOB_NAME);
        setJOB_GROUP(JOB_GROUP);
        setIS_NONCONCURRENT(IS_NONCONCURRENT);
        setREQUESTS_RECOVERY(REQUESTS_RECOVERY);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzFiredTriggersRecord
     */
    public QrtzFiredTriggersRecord(org.fk.database1.testshop.tables.pojos.QrtzFiredTriggers value) {
        super(QrtzFiredTriggers.QRTZ_FIRED_TRIGGERS);

        if (value != null) {
            setSCHED_NAME(value.getSCHED_NAME());
            setENTRY_ID(value.getENTRY_ID());
            setTRIGGER_NAME(value.getTRIGGER_NAME());
            setTRIGGER_GROUP(value.getTRIGGER_GROUP());
            setINSTANCE_NAME(value.getINSTANCE_NAME());
            setFIRED_TIME(value.getFIRED_TIME());
            setSCHED_TIME(value.getSCHED_TIME());
            setPRIORITY(value.getPRIORITY());
            setSTATE(value.getSTATE());
            setJOB_NAME(value.getJOB_NAME());
            setJOB_GROUP(value.getJOB_GROUP());
            setIS_NONCONCURRENT(value.getIS_NONCONCURRENT());
            setREQUESTS_RECOVERY(value.getREQUESTS_RECOVERY());
            resetChangedOnNotNull();
        }
    }
}
