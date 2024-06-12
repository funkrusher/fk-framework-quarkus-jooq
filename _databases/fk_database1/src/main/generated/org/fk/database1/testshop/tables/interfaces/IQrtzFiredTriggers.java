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
public interface IQrtzFiredTriggers extends Serializable {

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    public IQrtzFiredTriggers setSCHED_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    public String getSCHED_NAME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    public IQrtzFiredTriggers setENTRY_ID(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    @NotNull
    @Size(max = 95)
    public String getENTRY_ID();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    public IQrtzFiredTriggers setTRIGGER_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getTRIGGER_NAME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    public IQrtzFiredTriggers setTRIGGER_GROUP(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getTRIGGER_GROUP();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    public IQrtzFiredTriggers setINSTANCE_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getINSTANCE_NAME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    public IQrtzFiredTriggers setFIRED_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    @NotNull
    public Long getFIRED_TIME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    public IQrtzFiredTriggers setSCHED_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    @NotNull
    public Long getSCHED_TIME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    public IQrtzFiredTriggers setPRIORITY(Integer value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    @NotNull
    public Integer getPRIORITY();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    public IQrtzFiredTriggers setSTATE(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    @NotNull
    @Size(max = 16)
    public String getSTATE();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    public IQrtzFiredTriggers setJOB_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    @Size(max = 190)
    public String getJOB_NAME();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    public IQrtzFiredTriggers setJOB_GROUP(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    @Size(max = 190)
    public String getJOB_GROUP();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    public IQrtzFiredTriggers setIS_NONCONCURRENT(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    @Size(max = 1)
    public String getIS_NONCONCURRENT();

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    public IQrtzFiredTriggers setREQUESTS_RECOVERY(String value);

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    @Size(max = 1)
    public String getREQUESTS_RECOVERY();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IQrtzFiredTriggers
     */
    public void from(IQrtzFiredTriggers from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IQrtzFiredTriggers
     */
    public <E extends IQrtzFiredTriggers> E into(E into);
}
