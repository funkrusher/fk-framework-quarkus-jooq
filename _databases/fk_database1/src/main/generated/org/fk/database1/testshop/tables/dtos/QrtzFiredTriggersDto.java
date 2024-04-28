package org.fk.database1.testshop.tables.pojos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzFiredTriggers;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzFiredTriggersDto implements IQrtzFiredTriggers, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String ENTRY_ID;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private String INSTANCE_NAME;
    private Long FIRED_TIME;
    private Long SCHED_TIME;
    private Integer PRIORITY;
    private String STATE;
    private String JOB_NAME;
    private String JOB_GROUP;
    private String IS_NONCONCURRENT;
    private String REQUESTS_RECOVERY;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzFiredTriggersDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    @NotNull
    @Size(max = 95)
    @Override
    public String getENTRY_ID() {
        return this.ENTRY_ID;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.ENTRY_ID</code>.
     */
    @Override
    public QrtzFiredTriggersDto setENTRY_ID(String ENTRY_ID) {
        this.ENTRY_ID = ENTRY_ID;
        this.keeper.touch("ENTRY_ID");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return this.TRIGGER_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.keeper.touch("TRIGGER_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return this.TRIGGER_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public QrtzFiredTriggersDto setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.keeper.touch("TRIGGER_GROUP");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getINSTANCE_NAME() {
        return this.INSTANCE_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.INSTANCE_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setINSTANCE_NAME(String INSTANCE_NAME) {
        this.INSTANCE_NAME = INSTANCE_NAME;
        this.keeper.touch("INSTANCE_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    @NotNull
    @Override
    public Long getFIRED_TIME() {
        return this.FIRED_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.FIRED_TIME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setFIRED_TIME(Long FIRED_TIME) {
        this.FIRED_TIME = FIRED_TIME;
        this.keeper.touch("FIRED_TIME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    @NotNull
    @Override
    public Long getSCHED_TIME() {
        return this.SCHED_TIME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.SCHED_TIME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setSCHED_TIME(Long SCHED_TIME) {
        this.SCHED_TIME = SCHED_TIME;
        this.keeper.touch("SCHED_TIME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    @NotNull
    @Override
    public Integer getPRIORITY() {
        return this.PRIORITY;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.PRIORITY</code>.
     */
    @Override
    public QrtzFiredTriggersDto setPRIORITY(Integer PRIORITY) {
        this.PRIORITY = PRIORITY;
        this.keeper.touch("PRIORITY");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    @NotNull
    @Size(max = 16)
    @Override
    public String getSTATE() {
        return this.STATE;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.STATE</code>.
     */
    @Override
    public QrtzFiredTriggersDto setSTATE(String STATE) {
        this.STATE = STATE;
        this.keeper.touch("STATE");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    @Size(max = 190)
    @Override
    public String getJOB_NAME() {
        return this.JOB_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_NAME</code>.
     */
    @Override
    public QrtzFiredTriggersDto setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
        this.keeper.touch("JOB_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    @Size(max = 190)
    @Override
    public String getJOB_GROUP() {
        return this.JOB_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.JOB_GROUP</code>.
     */
    @Override
    public QrtzFiredTriggersDto setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
        this.keeper.touch("JOB_GROUP");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    @Size(max = 1)
    @Override
    public String getIS_NONCONCURRENT() {
        return this.IS_NONCONCURRENT;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.IS_NONCONCURRENT</code>.
     */
    @Override
    public QrtzFiredTriggersDto setIS_NONCONCURRENT(String IS_NONCONCURRENT) {
        this.IS_NONCONCURRENT = IS_NONCONCURRENT;
        this.keeper.touch("IS_NONCONCURRENT");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    @Size(max = 1)
    @Override
    public String getREQUESTS_RECOVERY() {
        return this.REQUESTS_RECOVERY;
    }

    /**
     * Setter for <code>testshop.QRTZ_FIRED_TRIGGERS.REQUESTS_RECOVERY</code>.
     */
    @Override
    public QrtzFiredTriggersDto setREQUESTS_RECOVERY(String REQUESTS_RECOVERY) {
        this.REQUESTS_RECOVERY = REQUESTS_RECOVERY;
        this.keeper.touch("REQUESTS_RECOVERY");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // ToString, Equals, HashCode
    // -------------------------------------------------------------------------
 
    @Override
    public String toString() {
        return keeper.touchedToString();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DTO other = (DTO) obj;
        return this.keeper.touchedEquals(other);
    }
 
    @Override
    public int hashCode() {
        return this.keeper.touchedHashCode();
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
    }
    @Override
    public <E extends IQrtzFiredTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
