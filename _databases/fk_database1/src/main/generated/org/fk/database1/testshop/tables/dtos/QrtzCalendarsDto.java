package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzCalendars;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzCalendarsDto implements IQrtzCalendars, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String CALENDAR_NAME;
    private byte[] CALENDAR;


    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzCalendarsDto() {}

    public QrtzCalendarsDto(IQrtzCalendars value) {
        this.setSCHED_NAME(value.getSCHED_NAME());
        this.setCALENDAR_NAME(value.getCALENDAR_NAME());
        this.setCALENDAR(value.getCALENDAR());
    }

    public QrtzCalendarsDto(
        String SCHED_NAME,
        String CALENDAR_NAME,
        byte[] CALENDAR
    ) {
        this.setSCHED_NAME(SCHED_NAME);
        this.setCALENDAR_NAME(CALENDAR_NAME);
        this.setCALENDAR(CALENDAR);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.SCHED_NAME</code>.
     */
    @Override
    public QrtzCalendarsDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.CALENDAR_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getCALENDAR_NAME() {
        return this.CALENDAR_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.CALENDAR_NAME</code>.
     */
    @Override
    public QrtzCalendarsDto setCALENDAR_NAME(String CALENDAR_NAME) {
        this.CALENDAR_NAME = CALENDAR_NAME;
        this.keeper.touch("CALENDAR_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.CALENDAR</code>.
     */
    @NotNull
    @Size(max = 65535)
    @Override
    public byte[] getCALENDAR() {
        return this.CALENDAR;
    }

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.CALENDAR</code>.
     */
    @Override
    public QrtzCalendarsDto setCALENDAR(byte[] CALENDAR) {
        this.CALENDAR = CALENDAR;
        this.keeper.touch("CALENDAR");
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
    public void from(IQrtzCalendars from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setCALENDAR_NAME(from.getCALENDAR_NAME());
        setCALENDAR(from.getCALENDAR());
    }

    @Override
    public <E extends IQrtzCalendars> E into(E into) {
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
