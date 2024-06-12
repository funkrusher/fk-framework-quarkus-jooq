/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzCronTriggers;
import org.fk.database1.testshop.tables.interfaces.IQrtzCronTriggers;
import org.jooq.Record3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzCronTriggersRecord extends UpdatableRecordImpl<QrtzCronTriggersRecord> implements IQrtzCronTriggers {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzCronTriggersRecord SCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String SCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzCronTriggersRecord TRIGGER_NAME(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String TRIGGER_NAME() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzCronTriggersRecord TRIGGER_GROUP(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String TRIGGER_GROUP() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.CRON_EXPRESSION</code>.
     */
    @Override
    public QrtzCronTriggersRecord CRON_EXPRESSION(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.CRON_EXPRESSION</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String CRON_EXPRESSION() {
        return (String) get(3);
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TIME_ZONE_ID</code>.
     */
    @Override
    public QrtzCronTriggersRecord TIME_ZONE_ID(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TIME_ZONE_ID</code>.
     */
    @Size(max = 80)
    @Override
    public String TIME_ZONE_ID() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, String> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzCronTriggers from) {
        SCHED_NAME(from.SCHED_NAME());
        TRIGGER_NAME(from.TRIGGER_NAME());
        TRIGGER_GROUP(from.TRIGGER_GROUP());
        CRON_EXPRESSION(from.CRON_EXPRESSION());
        TIME_ZONE_ID(from.TIME_ZONE_ID());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzCronTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzCronTriggersRecord
     */
    public QrtzCronTriggersRecord() {
        super(QrtzCronTriggers.QRTZ_CRON_TRIGGERS);
    }

    /**
     * Create a detached, initialised QrtzCronTriggersRecord
     */
    public QrtzCronTriggersRecord(String SCHED_NAME, String TRIGGER_NAME, String TRIGGER_GROUP, String CRON_EXPRESSION, String TIME_ZONE_ID) {
        super(QrtzCronTriggers.QRTZ_CRON_TRIGGERS);

        SCHED_NAME(SCHED_NAME);
        TRIGGER_NAME(TRIGGER_NAME);
        TRIGGER_GROUP(TRIGGER_GROUP);
        CRON_EXPRESSION(CRON_EXPRESSION);
        TIME_ZONE_ID(TIME_ZONE_ID);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzCronTriggersRecord
     */
    public QrtzCronTriggersRecord(org.fk.database1.testshop.tables.pojos.QrtzCronTriggers value) {
        super(QrtzCronTriggers.QRTZ_CRON_TRIGGERS);

        if (value != null) {
            SCHED_NAME(value.SCHED_NAME());
            TRIGGER_NAME(value.TRIGGER_NAME());
            TRIGGER_GROUP(value.TRIGGER_GROUP());
            CRON_EXPRESSION(value.CRON_EXPRESSION());
            TIME_ZONE_ID(value.TIME_ZONE_ID());
            resetChangedOnNotNull();
        }
    }
}
