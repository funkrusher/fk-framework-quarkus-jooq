/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzTriggers implements IQrtzTriggers {

    private static final long serialVersionUID = 1L;

    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private String JOB_NAME;
    private String JOB_GROUP;
    private String DESCRIPTION;
    private Long NEXT_FIRE_TIME;
    private Long PREV_FIRE_TIME;
    private Integer PRIORITY;
    private String TRIGGER_STATE;
    private String TRIGGER_TYPE;
    private Long START_TIME;
    private Long END_TIME;
    private String CALENDAR_NAME;
    private Short MISFIRE_INSTR;
    private byte[] JOB_DATA;

    public QrtzTriggers() {}

    public QrtzTriggers(IQrtzTriggers value) {
        this.SCHED_NAME = value.getSCHED_NAME();
        this.TRIGGER_NAME = value.getTRIGGER_NAME();
        this.TRIGGER_GROUP = value.getTRIGGER_GROUP();
        this.JOB_NAME = value.getJOB_NAME();
        this.JOB_GROUP = value.getJOB_GROUP();
        this.DESCRIPTION = value.getDESCRIPTION();
        this.NEXT_FIRE_TIME = value.getNEXT_FIRE_TIME();
        this.PREV_FIRE_TIME = value.getPREV_FIRE_TIME();
        this.PRIORITY = value.getPRIORITY();
        this.TRIGGER_STATE = value.getTRIGGER_STATE();
        this.TRIGGER_TYPE = value.getTRIGGER_TYPE();
        this.START_TIME = value.getSTART_TIME();
        this.END_TIME = value.getEND_TIME();
        this.CALENDAR_NAME = value.getCALENDAR_NAME();
        this.MISFIRE_INSTR = value.getMISFIRE_INSTR();
        this.JOB_DATA = value.getJOB_DATA();
    }

    public QrtzTriggers(
        String SCHED_NAME,
        String TRIGGER_NAME,
        String TRIGGER_GROUP,
        String JOB_NAME,
        String JOB_GROUP,
        String DESCRIPTION,
        Long NEXT_FIRE_TIME,
        Long PREV_FIRE_TIME,
        Integer PRIORITY,
        String TRIGGER_STATE,
        String TRIGGER_TYPE,
        Long START_TIME,
        Long END_TIME,
        String CALENDAR_NAME,
        Short MISFIRE_INSTR,
        byte[] JOB_DATA
    ) {
        this.SCHED_NAME = SCHED_NAME;
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.JOB_NAME = JOB_NAME;
        this.JOB_GROUP = JOB_GROUP;
        this.DESCRIPTION = DESCRIPTION;
        this.NEXT_FIRE_TIME = NEXT_FIRE_TIME;
        this.PREV_FIRE_TIME = PREV_FIRE_TIME;
        this.PRIORITY = PRIORITY;
        this.TRIGGER_STATE = TRIGGER_STATE;
        this.TRIGGER_TYPE = TRIGGER_TYPE;
        this.START_TIME = START_TIME;
        this.END_TIME = END_TIME;
        this.CALENDAR_NAME = CALENDAR_NAME;
        this.MISFIRE_INSTR = MISFIRE_INSTR;
        this.JOB_DATA = JOB_DATA;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzTriggers setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return this.TRIGGER_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzTriggers setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return this.TRIGGER_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzTriggers setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getJOB_NAME() {
        return this.JOB_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_NAME</code>.
     */
    @Override
    public QrtzTriggers setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getJOB_GROUP() {
        return this.JOB_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_GROUP</code>.
     */
    @Override
    public QrtzTriggers setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.DESCRIPTION</code>.
     */
    @Size(max = 250)
    @Override
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.DESCRIPTION</code>.
     */
    @Override
    public QrtzTriggers setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.NEXT_FIRE_TIME</code>.
     */
    @Override
    public Long getNEXT_FIRE_TIME() {
        return this.NEXT_FIRE_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.NEXT_FIRE_TIME</code>.
     */
    @Override
    public QrtzTriggers setNEXT_FIRE_TIME(Long NEXT_FIRE_TIME) {
        this.NEXT_FIRE_TIME = NEXT_FIRE_TIME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.PREV_FIRE_TIME</code>.
     */
    @Override
    public Long getPREV_FIRE_TIME() {
        return this.PREV_FIRE_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.PREV_FIRE_TIME</code>.
     */
    @Override
    public QrtzTriggers setPREV_FIRE_TIME(Long PREV_FIRE_TIME) {
        this.PREV_FIRE_TIME = PREV_FIRE_TIME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.PRIORITY</code>.
     */
    @Override
    public Integer getPRIORITY() {
        return this.PRIORITY;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.PRIORITY</code>.
     */
    @Override
    public QrtzTriggers setPRIORITY(Integer PRIORITY) {
        this.PRIORITY = PRIORITY;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_STATE</code>.
     */
    @NotNull
    @Size(max = 16)
    @Override
    public String getTRIGGER_STATE() {
        return this.TRIGGER_STATE;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_STATE</code>.
     */
    @Override
    public QrtzTriggers setTRIGGER_STATE(String TRIGGER_STATE) {
        this.TRIGGER_STATE = TRIGGER_STATE;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_TYPE</code>.
     */
    @NotNull
    @Size(max = 8)
    @Override
    public String getTRIGGER_TYPE() {
        return this.TRIGGER_TYPE;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.TRIGGER_TYPE</code>.
     */
    @Override
    public QrtzTriggers setTRIGGER_TYPE(String TRIGGER_TYPE) {
        this.TRIGGER_TYPE = TRIGGER_TYPE;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.START_TIME</code>.
     */
    @NotNull
    @Override
    public Long getSTART_TIME() {
        return this.START_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.START_TIME</code>.
     */
    @Override
    public QrtzTriggers setSTART_TIME(Long START_TIME) {
        this.START_TIME = START_TIME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.END_TIME</code>.
     */
    @Override
    public Long getEND_TIME() {
        return this.END_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.END_TIME</code>.
     */
    @Override
    public QrtzTriggers setEND_TIME(Long END_TIME) {
        this.END_TIME = END_TIME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.CALENDAR_NAME</code>.
     */
    @Size(max = 190)
    @Override
    public String getCALENDAR_NAME() {
        return this.CALENDAR_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.CALENDAR_NAME</code>.
     */
    @Override
    public QrtzTriggers setCALENDAR_NAME(String CALENDAR_NAME) {
        this.CALENDAR_NAME = CALENDAR_NAME;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.MISFIRE_INSTR</code>.
     */
    @Override
    public Short getMISFIRE_INSTR() {
        return this.MISFIRE_INSTR;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.MISFIRE_INSTR</code>.
     */
    @Override
    public QrtzTriggers setMISFIRE_INSTR(Short MISFIRE_INSTR) {
        this.MISFIRE_INSTR = MISFIRE_INSTR;
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_TRIGGERS.JOB_DATA</code>.
     */
    @Size(max = 65535)
    @Override
    public byte[] getJOB_DATA() {
        return this.JOB_DATA;
    }

    /**
     * Setter for <code>testshop.QRTZ_TRIGGERS.JOB_DATA</code>.
     */
    @Override
    public QrtzTriggers setJOB_DATA(byte[] JOB_DATA) {
        this.JOB_DATA = JOB_DATA;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QrtzTriggers (");

        sb.append(SCHED_NAME);
        sb.append(", ").append(TRIGGER_NAME);
        sb.append(", ").append(TRIGGER_GROUP);
        sb.append(", ").append(JOB_NAME);
        sb.append(", ").append(JOB_GROUP);
        sb.append(", ").append(DESCRIPTION);
        sb.append(", ").append(NEXT_FIRE_TIME);
        sb.append(", ").append(PREV_FIRE_TIME);
        sb.append(", ").append(PRIORITY);
        sb.append(", ").append(TRIGGER_STATE);
        sb.append(", ").append(TRIGGER_TYPE);
        sb.append(", ").append(START_TIME);
        sb.append(", ").append(END_TIME);
        sb.append(", ").append(CALENDAR_NAME);
        sb.append(", ").append(MISFIRE_INSTR);
        sb.append(", ").append("[binary...]");

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzTriggers from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setTRIGGER_NAME(from.getTRIGGER_NAME());
        setTRIGGER_GROUP(from.getTRIGGER_GROUP());
        setJOB_NAME(from.getJOB_NAME());
        setJOB_GROUP(from.getJOB_GROUP());
        setDESCRIPTION(from.getDESCRIPTION());
        setNEXT_FIRE_TIME(from.getNEXT_FIRE_TIME());
        setPREV_FIRE_TIME(from.getPREV_FIRE_TIME());
        setPRIORITY(from.getPRIORITY());
        setTRIGGER_STATE(from.getTRIGGER_STATE());
        setTRIGGER_TYPE(from.getTRIGGER_TYPE());
        setSTART_TIME(from.getSTART_TIME());
        setEND_TIME(from.getEND_TIME());
        setCALENDAR_NAME(from.getCALENDAR_NAME());
        setMISFIRE_INSTR(from.getMISFIRE_INSTR());
        setJOB_DATA(from.getJOB_DATA());
    }

    @Override
    public <E extends IQrtzTriggers> E into(E into) {
        into.from(this);
        return into;
    }
}
