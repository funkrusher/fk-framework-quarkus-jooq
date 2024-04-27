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
public interface IQrtzTriggers extends Serializable {

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.SCHED_NAME</code>.
     */
    public IQrtzTriggers setSCHED_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    public String getSCHED_NAME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_NAME</code>.
     */
    public IQrtzTriggers setTRIGGER_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getTRIGGER_NAME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_GROUP</code>.
     */
    public IQrtzTriggers setTRIGGER_GROUP(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getTRIGGER_GROUP();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_NAME</code>.
     */
    public IQrtzTriggers setJOB_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getJOB_NAME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_GROUP</code>.
     */
    public IQrtzTriggers setJOB_GROUP(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    public String getJOB_GROUP();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.DESCRIPTION</code>.
     */
    public IQrtzTriggers setDESCRIPTION(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.DESCRIPTION</code>.
     */
    @Size(max = 250)
    public String getDESCRIPTION();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.NEXT_FIRE_TIME</code>.
     */
    public IQrtzTriggers setNEXT_FIRE_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.NEXT_FIRE_TIME</code>.
     */
    public Long getNEXT_FIRE_TIME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.PREV_FIRE_TIME</code>.
     */
    public IQrtzTriggers setPREV_FIRE_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.PREV_FIRE_TIME</code>.
     */
    public Long getPREV_FIRE_TIME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.PRIORITY</code>.
     */
    public IQrtzTriggers setPRIORITY(Integer value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.PRIORITY</code>.
     */
    public Integer getPRIORITY();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_STATE</code>.
     */
    public IQrtzTriggers setTRIGGER_STATE(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_STATE</code>.
     */
    @NotNull
    @Size(max = 16)
    public String getTRIGGER_STATE();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_TYPE</code>.
     */
    public IQrtzTriggers setTRIGGER_TYPE(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_TYPE</code>.
     */
    @NotNull
    @Size(max = 8)
    public String getTRIGGER_TYPE();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.START_TIME</code>.
     */
    public IQrtzTriggers setSTART_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.START_TIME</code>.
     */
    @NotNull
    public Long getSTART_TIME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.END_TIME</code>.
     */
    public IQrtzTriggers setEND_TIME(Long value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.END_TIME</code>.
     */
    public Long getEND_TIME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.CALENDAR_NAME</code>.
     */
    public IQrtzTriggers setCALENDAR_NAME(String value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.CALENDAR_NAME</code>.
     */
    @Size(max = 190)
    public String getCALENDAR_NAME();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.MISFIRE_INSTR</code>.
     */
    public IQrtzTriggers setMISFIRE_INSTR(Short value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.MISFIRE_INSTR</code>.
     */
    public Short getMISFIRE_INSTR();

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_DATA</code>.
     */
    public IQrtzTriggers setJOB_DATA(byte[] value);

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_DATA</code>.
     */
    @Size(max = 65535)
    public byte[] getJOB_DATA();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IQrtzTriggers
     */
    public void from(IQrtzTriggers from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IQrtzTriggers
     */
    public <E extends IQrtzTriggers> E into(E into);
}
