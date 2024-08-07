/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.QrtzCalendars;
import org.fk.database1.testshop.tables.interfaces.IQrtzCalendars;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzCalendarsRecord extends UpdatableRecordImpl<QrtzCalendarsRecord> implements IQrtzCalendars {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.SCHED_NAME</code>.
     */
    @Override
    public QrtzCalendarsRecord setSCHED_NAME(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.CALENDAR_NAME</code>.
     */
    @Override
    public QrtzCalendarsRecord setCALENDAR_NAME(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.CALENDAR_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getCALENDAR_NAME() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.QRTZ_CALENDARS.CALENDAR</code>.
     */
    @Override
    public QrtzCalendarsRecord setCALENDAR(byte[] value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_CALENDARS.CALENDAR</code>.
     */
    @NotNull
    @Size(max = 65535)
    @Override
    public byte[] getCALENDAR() {
        return (byte[]) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzCalendars from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setCALENDAR_NAME(from.getCALENDAR_NAME());
        setCALENDAR(from.getCALENDAR());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzCalendars> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzCalendarsRecord
     */
    public QrtzCalendarsRecord() {
        super(QrtzCalendars.QRTZ_CALENDARS);
    }

    /**
     * Create a detached, initialised QrtzCalendarsRecord
     */
    public QrtzCalendarsRecord(String SCHED_NAME, String CALENDAR_NAME, byte[] CALENDAR) {
        super(QrtzCalendars.QRTZ_CALENDARS);

        setSCHED_NAME(SCHED_NAME);
        setCALENDAR_NAME(CALENDAR_NAME);
        setCALENDAR(CALENDAR);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzCalendarsRecord
     */
    public QrtzCalendarsRecord(org.fk.database1.testshop.tables.pojos.QrtzCalendars value) {
        super(QrtzCalendars.QRTZ_CALENDARS);

        if (value != null) {
            setSCHED_NAME(value.getSCHED_NAME());
            setCALENDAR_NAME(value.getCALENDAR_NAME());
            setCALENDAR(value.getCALENDAR());
            resetChangedOnNotNull();
        }
    }
}
