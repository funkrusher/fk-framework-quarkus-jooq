/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzCronTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzCronTriggers implements IQrtzCronTriggers {

    private static final long serialVersionUID = 1L;

    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private String CRON_EXPRESSION;
    private String TIME_ZONE_ID;

    public QrtzCronTriggers() {}

    public QrtzCronTriggers(IQrtzCronTriggers value) {
        this.SCHED_NAME = value.getSCHED_NAME();
        this.TRIGGER_NAME = value.getTRIGGER_NAME();
        this.TRIGGER_GROUP = value.getTRIGGER_GROUP();
        this.CRON_EXPRESSION = value.getCRON_EXPRESSION();
        this.TIME_ZONE_ID = value.getTIME_ZONE_ID();
    }

    public QrtzCronTriggers(
        String SCHED_NAME,
        String TRIGGER_NAME,
        String TRIGGER_GROUP,
        String CRON_EXPRESSION,
        String TIME_ZONE_ID
    ) {
        this.SCHED_NAME = SCHED_NAME;
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.CRON_EXPRESSION = CRON_EXPRESSION;
        this.TIME_ZONE_ID = TIME_ZONE_ID;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzCronTriggers setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return this.TRIGGER_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzCronTriggers setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return this.TRIGGER_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzCronTriggers setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.CRON_EXPRESSION</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getCRON_EXPRESSION() {
        return this.CRON_EXPRESSION;
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.CRON_EXPRESSION</code>.
     */
    @Override
    public QrtzCronTriggers setCRON_EXPRESSION(String CRON_EXPRESSION) {
        this.CRON_EXPRESSION = CRON_EXPRESSION;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CRON_TRIGGERS.TIME_ZONE_ID</code>.
     */
    @Size(max = 80)
    @Override
    public String getTIME_ZONE_ID() {
        return this.TIME_ZONE_ID;
    }

    /**
     * Setter for <code>testshop.QRTZ_CRON_TRIGGERS.TIME_ZONE_ID</code>.
     */
    @Override
    public QrtzCronTriggers setTIME_ZONE_ID(String TIME_ZONE_ID) {
        this.TIME_ZONE_ID = TIME_ZONE_ID;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QrtzCronTriggers (");

        sb.append(SCHED_NAME);
        sb.append(", ").append(TRIGGER_NAME);
        sb.append(", ").append(TRIGGER_GROUP);
        sb.append(", ").append(CRON_EXPRESSION);
        sb.append(", ").append(TIME_ZONE_ID);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzCronTriggers from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setTRIGGER_NAME(from.getTRIGGER_NAME());
        setTRIGGER_GROUP(from.getTRIGGER_GROUP());
        setCRON_EXPRESSION(from.getCRON_EXPRESSION());
        setTIME_ZONE_ID(from.getTIME_ZONE_ID());
    }

    @Override
    public <E extends IQrtzCronTriggers> E into(E into) {
        into.from(this);
        return into;
    }
}
