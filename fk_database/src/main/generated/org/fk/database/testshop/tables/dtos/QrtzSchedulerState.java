/*
 * This file is generated by jOOQ.
 */
package org.fk.database.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.core.dto.AbstractDTO;
import org.fk.database.testshop.tables.interfaces.IQrtzSchedulerState;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzSchedulerState extends AbstractDTO implements IQrtzSchedulerState {

    private static final long serialVersionUID = 1L;

    private String SCHED_NAME;
    private String INSTANCE_NAME;
    private Long LAST_CHECKIN_TIME;
    private Long CHECKIN_INTERVAL;

    public QrtzSchedulerState() {}

    public QrtzSchedulerState(IQrtzSchedulerState value) {
        this.SCHED_NAME = value.getSCHED_NAME();
        this.INSTANCE_NAME = value.getINSTANCE_NAME();
        this.LAST_CHECKIN_TIME = value.getLAST_CHECKIN_TIME();
        this.CHECKIN_INTERVAL = value.getCHECKIN_INTERVAL();
    }

    public QrtzSchedulerState(
        String SCHED_NAME,
        String INSTANCE_NAME,
        Long LAST_CHECKIN_TIME,
        Long CHECKIN_INTERVAL
    ) {
        this.SCHED_NAME = SCHED_NAME;
        this.INSTANCE_NAME = INSTANCE_NAME;
        this.LAST_CHECKIN_TIME = LAST_CHECKIN_TIME;
        this.CHECKIN_INTERVAL = CHECKIN_INTERVAL;
    }

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.SCHED_NAME</code>.
     */
    @Override
    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.touch();
    }

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.INSTANCE_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getINSTANCE_NAME() {
        return this.INSTANCE_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.INSTANCE_NAME</code>.
     */
    @Override
    public void setINSTANCE_NAME(String INSTANCE_NAME) {
        this.INSTANCE_NAME = INSTANCE_NAME;
        this.touch();
    }

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.LAST_CHECKIN_TIME</code>.
     */
    @NotNull
    @Override
    public Long getLAST_CHECKIN_TIME() {
        return this.LAST_CHECKIN_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.LAST_CHECKIN_TIME</code>.
     */
    @Override
    public void setLAST_CHECKIN_TIME(Long LAST_CHECKIN_TIME) {
        this.LAST_CHECKIN_TIME = LAST_CHECKIN_TIME;
        this.touch();
    }

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.CHECKIN_INTERVAL</code>.
     */
    @NotNull
    @Override
    public Long getCHECKIN_INTERVAL() {
        return this.CHECKIN_INTERVAL;
    }

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.CHECKIN_INTERVAL</code>.
     */
    @Override
    public void setCHECKIN_INTERVAL(Long CHECKIN_INTERVAL) {
        this.CHECKIN_INTERVAL = CHECKIN_INTERVAL;
        this.touch();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final QrtzSchedulerState other = (QrtzSchedulerState) obj;
        if (this.SCHED_NAME == null) {
            if (other.SCHED_NAME != null)
                return false;
        }
        else if (!this.SCHED_NAME.equals(other.SCHED_NAME))
            return false;
        if (this.INSTANCE_NAME == null) {
            if (other.INSTANCE_NAME != null)
                return false;
        }
        else if (!this.INSTANCE_NAME.equals(other.INSTANCE_NAME))
            return false;
        if (this.LAST_CHECKIN_TIME == null) {
            if (other.LAST_CHECKIN_TIME != null)
                return false;
        }
        else if (!this.LAST_CHECKIN_TIME.equals(other.LAST_CHECKIN_TIME))
            return false;
        if (this.CHECKIN_INTERVAL == null) {
            if (other.CHECKIN_INTERVAL != null)
                return false;
        }
        else if (!this.CHECKIN_INTERVAL.equals(other.CHECKIN_INTERVAL))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.SCHED_NAME == null) ? 0 : this.SCHED_NAME.hashCode());
        result = prime * result + ((this.INSTANCE_NAME == null) ? 0 : this.INSTANCE_NAME.hashCode());
        result = prime * result + ((this.LAST_CHECKIN_TIME == null) ? 0 : this.LAST_CHECKIN_TIME.hashCode());
        result = prime * result + ((this.CHECKIN_INTERVAL == null) ? 0 : this.CHECKIN_INTERVAL.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QrtzSchedulerState (");

        sb.append(SCHED_NAME);
        sb.append(", ").append(INSTANCE_NAME);
        sb.append(", ").append(LAST_CHECKIN_TIME);
        sb.append(", ").append(CHECKIN_INTERVAL);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzSchedulerState from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setINSTANCE_NAME(from.getINSTANCE_NAME());
        setLAST_CHECKIN_TIME(from.getLAST_CHECKIN_TIME());
        setCHECKIN_INTERVAL(from.getCHECKIN_INTERVAL());
    }

    @Override
    public <E extends IQrtzSchedulerState> E into(E into) {
        into.from(this);
        return into;
    }
}