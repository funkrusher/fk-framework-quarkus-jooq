package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzSchedulerState;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzSchedulerStateDto implements IQrtzSchedulerState, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String INSTANCE_NAME;
    private Long LAST_CHECKIN_TIME;
    private Long CHECKIN_INTERVAL;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzSchedulerStateDto() {}

    public static QrtzSchedulerStateDto create(
        String SCHED_NAME,
        String INSTANCE_NAME,
        Long LAST_CHECKIN_TIME,
        Long CHECKIN_INTERVAL
    ) {
        return new QrtzSchedulerStateDto()
            .setSCHED_NAME(SCHED_NAME)
            .setINSTANCE_NAME(INSTANCE_NAME)
            .setLAST_CHECKIN_TIME(LAST_CHECKIN_TIME)
            .setCHECKIN_INTERVAL(CHECKIN_INTERVAL);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
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
    public QrtzSchedulerStateDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return this;
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
    public QrtzSchedulerStateDto setINSTANCE_NAME(String INSTANCE_NAME) {
        this.INSTANCE_NAME = INSTANCE_NAME;
        this.keeper.touch("INSTANCE_NAME");
        return this;
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
    public QrtzSchedulerStateDto setLAST_CHECKIN_TIME(Long LAST_CHECKIN_TIME) {
        this.LAST_CHECKIN_TIME = LAST_CHECKIN_TIME;
        this.keeper.touch("LAST_CHECKIN_TIME");
        return this;
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
    public QrtzSchedulerStateDto setCHECKIN_INTERVAL(Long CHECKIN_INTERVAL) {
        this.CHECKIN_INTERVAL = CHECKIN_INTERVAL;
        this.keeper.touch("CHECKIN_INTERVAL");
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

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper = new BookKeeper(this);
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
