/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.codegen.dto.AbstractDTO;
import org.fk.codegen.testshop.tables.interfaces.IQrtzCronTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzCronTriggers extends AbstractDTO implements IQrtzCronTriggers {

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
    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.setAt("SCHED_NAME", SCHED_NAME);
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
    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.setAt("TRIGGER_NAME", TRIGGER_NAME);
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
    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.setAt("TRIGGER_GROUP", TRIGGER_GROUP);
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
    public void setCRON_EXPRESSION(String CRON_EXPRESSION) {
        this.CRON_EXPRESSION = CRON_EXPRESSION;
        this.setAt("CRON_EXPRESSION", CRON_EXPRESSION);
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
    public void setTIME_ZONE_ID(String TIME_ZONE_ID) {
        this.TIME_ZONE_ID = TIME_ZONE_ID;
        this.setAt("TIME_ZONE_ID", TIME_ZONE_ID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final QrtzCronTriggers other = (QrtzCronTriggers) obj;
        if (this.SCHED_NAME == null) {
            if (other.SCHED_NAME != null)
                return false;
        }
        else if (!this.SCHED_NAME.equals(other.SCHED_NAME))
            return false;
        if (this.TRIGGER_NAME == null) {
            if (other.TRIGGER_NAME != null)
                return false;
        }
        else if (!this.TRIGGER_NAME.equals(other.TRIGGER_NAME))
            return false;
        if (this.TRIGGER_GROUP == null) {
            if (other.TRIGGER_GROUP != null)
                return false;
        }
        else if (!this.TRIGGER_GROUP.equals(other.TRIGGER_GROUP))
            return false;
        if (this.CRON_EXPRESSION == null) {
            if (other.CRON_EXPRESSION != null)
                return false;
        }
        else if (!this.CRON_EXPRESSION.equals(other.CRON_EXPRESSION))
            return false;
        if (this.TIME_ZONE_ID == null) {
            if (other.TIME_ZONE_ID != null)
                return false;
        }
        else if (!this.TIME_ZONE_ID.equals(other.TIME_ZONE_ID))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.SCHED_NAME == null) ? 0 : this.SCHED_NAME.hashCode());
        result = prime * result + ((this.TRIGGER_NAME == null) ? 0 : this.TRIGGER_NAME.hashCode());
        result = prime * result + ((this.TRIGGER_GROUP == null) ? 0 : this.TRIGGER_GROUP.hashCode());
        result = prime * result + ((this.CRON_EXPRESSION == null) ? 0 : this.CRON_EXPRESSION.hashCode());
        result = prime * result + ((this.TIME_ZONE_ID == null) ? 0 : this.TIME_ZONE_ID.hashCode());
        return result;
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
