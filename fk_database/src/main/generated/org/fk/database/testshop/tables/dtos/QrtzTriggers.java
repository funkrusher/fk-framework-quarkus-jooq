/*
 * This file is generated by jOOQ.
 */
package org.fk.database.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Arrays;

import org.fk.core.dto.AbstractDTO;
import org.fk.database.testshop.tables.interfaces.IQrtzTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzTriggers extends AbstractDTO implements IQrtzTriggers {

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
    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.touch();
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
    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.touch();
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
    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.touch();
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
    public void setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
        this.touch();
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
    public void setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
        this.touch();
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
    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
        this.touch();
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
    public void setNEXT_FIRE_TIME(Long NEXT_FIRE_TIME) {
        this.NEXT_FIRE_TIME = NEXT_FIRE_TIME;
        this.touch();
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
    public void setPREV_FIRE_TIME(Long PREV_FIRE_TIME) {
        this.PREV_FIRE_TIME = PREV_FIRE_TIME;
        this.touch();
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
    public void setPRIORITY(Integer PRIORITY) {
        this.PRIORITY = PRIORITY;
        this.touch();
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
    public void setTRIGGER_STATE(String TRIGGER_STATE) {
        this.TRIGGER_STATE = TRIGGER_STATE;
        this.touch();
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
    public void setTRIGGER_TYPE(String TRIGGER_TYPE) {
        this.TRIGGER_TYPE = TRIGGER_TYPE;
        this.touch();
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
    public void setSTART_TIME(Long START_TIME) {
        this.START_TIME = START_TIME;
        this.touch();
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
    public void setEND_TIME(Long END_TIME) {
        this.END_TIME = END_TIME;
        this.touch();
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
    public void setCALENDAR_NAME(String CALENDAR_NAME) {
        this.CALENDAR_NAME = CALENDAR_NAME;
        this.touch();
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
    public void setMISFIRE_INSTR(Short MISFIRE_INSTR) {
        this.MISFIRE_INSTR = MISFIRE_INSTR;
        this.touch();
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
    public void setJOB_DATA(byte[] JOB_DATA) {
        this.JOB_DATA = JOB_DATA;
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
        final QrtzTriggers other = (QrtzTriggers) obj;
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
        if (this.JOB_NAME == null) {
            if (other.JOB_NAME != null)
                return false;
        }
        else if (!this.JOB_NAME.equals(other.JOB_NAME))
            return false;
        if (this.JOB_GROUP == null) {
            if (other.JOB_GROUP != null)
                return false;
        }
        else if (!this.JOB_GROUP.equals(other.JOB_GROUP))
            return false;
        if (this.DESCRIPTION == null) {
            if (other.DESCRIPTION != null)
                return false;
        }
        else if (!this.DESCRIPTION.equals(other.DESCRIPTION))
            return false;
        if (this.NEXT_FIRE_TIME == null) {
            if (other.NEXT_FIRE_TIME != null)
                return false;
        }
        else if (!this.NEXT_FIRE_TIME.equals(other.NEXT_FIRE_TIME))
            return false;
        if (this.PREV_FIRE_TIME == null) {
            if (other.PREV_FIRE_TIME != null)
                return false;
        }
        else if (!this.PREV_FIRE_TIME.equals(other.PREV_FIRE_TIME))
            return false;
        if (this.PRIORITY == null) {
            if (other.PRIORITY != null)
                return false;
        }
        else if (!this.PRIORITY.equals(other.PRIORITY))
            return false;
        if (this.TRIGGER_STATE == null) {
            if (other.TRIGGER_STATE != null)
                return false;
        }
        else if (!this.TRIGGER_STATE.equals(other.TRIGGER_STATE))
            return false;
        if (this.TRIGGER_TYPE == null) {
            if (other.TRIGGER_TYPE != null)
                return false;
        }
        else if (!this.TRIGGER_TYPE.equals(other.TRIGGER_TYPE))
            return false;
        if (this.START_TIME == null) {
            if (other.START_TIME != null)
                return false;
        }
        else if (!this.START_TIME.equals(other.START_TIME))
            return false;
        if (this.END_TIME == null) {
            if (other.END_TIME != null)
                return false;
        }
        else if (!this.END_TIME.equals(other.END_TIME))
            return false;
        if (this.CALENDAR_NAME == null) {
            if (other.CALENDAR_NAME != null)
                return false;
        }
        else if (!this.CALENDAR_NAME.equals(other.CALENDAR_NAME))
            return false;
        if (this.MISFIRE_INSTR == null) {
            if (other.MISFIRE_INSTR != null)
                return false;
        }
        else if (!this.MISFIRE_INSTR.equals(other.MISFIRE_INSTR))
            return false;
        if (this.JOB_DATA == null) {
            if (other.JOB_DATA != null)
                return false;
        }
        else if (!Arrays.equals(this.JOB_DATA, other.JOB_DATA))
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
        result = prime * result + ((this.JOB_NAME == null) ? 0 : this.JOB_NAME.hashCode());
        result = prime * result + ((this.JOB_GROUP == null) ? 0 : this.JOB_GROUP.hashCode());
        result = prime * result + ((this.DESCRIPTION == null) ? 0 : this.DESCRIPTION.hashCode());
        result = prime * result + ((this.NEXT_FIRE_TIME == null) ? 0 : this.NEXT_FIRE_TIME.hashCode());
        result = prime * result + ((this.PREV_FIRE_TIME == null) ? 0 : this.PREV_FIRE_TIME.hashCode());
        result = prime * result + ((this.PRIORITY == null) ? 0 : this.PRIORITY.hashCode());
        result = prime * result + ((this.TRIGGER_STATE == null) ? 0 : this.TRIGGER_STATE.hashCode());
        result = prime * result + ((this.TRIGGER_TYPE == null) ? 0 : this.TRIGGER_TYPE.hashCode());
        result = prime * result + ((this.START_TIME == null) ? 0 : this.START_TIME.hashCode());
        result = prime * result + ((this.END_TIME == null) ? 0 : this.END_TIME.hashCode());
        result = prime * result + ((this.CALENDAR_NAME == null) ? 0 : this.CALENDAR_NAME.hashCode());
        result = prime * result + ((this.MISFIRE_INSTR == null) ? 0 : this.MISFIRE_INSTR.hashCode());
        result = prime * result + ((this.JOB_DATA == null) ? 0 : Arrays.hashCode(this.JOB_DATA));
        return result;
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