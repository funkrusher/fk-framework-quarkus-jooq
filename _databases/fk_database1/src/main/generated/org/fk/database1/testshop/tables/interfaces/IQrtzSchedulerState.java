/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.interfaces;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IQrtzSchedulerState extends Serializable {

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.SCHED_NAME</code>.
     */
    public IQrtzSchedulerState setSCHED_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    public String getSCHED_NAME();

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.INSTANCE_NAME</code>.
     */
    public IQrtzSchedulerState setINSTANCE_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.INSTANCE_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getINSTANCE_NAME();

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.LAST_CHECKIN_TIME</code>.
     */
    public IQrtzSchedulerState setLAST_CHECKIN_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.LAST_CHECKIN_TIME</code>.
     */
    @NotNull
    public Long getLAST_CHECKIN_TIME();

    /**
     * Setter for <code>testshop.QRTZ_SCHEDULER_STATE.CHECKIN_INTERVAL</code>.
     */
    public IQrtzSchedulerState setCHECKIN_INTERVAL(Long value);

    /**
     * Getter for <code>testshop.QRTZ_SCHEDULER_STATE.CHECKIN_INTERVAL</code>.
     */
    @NotNull
    public Long getCHECKIN_INTERVAL();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IQrtzSchedulerState
     */
    public void from(IQrtzSchedulerState from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IQrtzSchedulerState
     */
    public <E extends IQrtzSchedulerState> E into(E into);
}
