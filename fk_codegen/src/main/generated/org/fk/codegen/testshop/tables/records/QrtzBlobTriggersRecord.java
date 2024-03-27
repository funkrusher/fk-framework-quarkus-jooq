/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.codegen.testshop.tables.QrtzBlobTriggers;
import org.fk.codegen.testshop.tables.interfaces.IQrtzBlobTriggers;
import org.jooq.Record3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzBlobTriggersRecord extends UpdatableRecordImpl<QrtzBlobTriggersRecord> implements IQrtzBlobTriggers {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public void setSCHED_NAME(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public void setTRIGGER_NAME(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public void setTRIGGER_GROUP(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.BLOB_DATA</code>.
     */
    @Override
    public void setBLOB_DATA(byte[] value) {
        set(3, value);
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.BLOB_DATA</code>.
     */
    @Size(max = 65535)
    @Override
    public byte[] getBLOB_DATA() {
        return (byte[]) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<String, String, String> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IQrtzBlobTriggers from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setTRIGGER_NAME(from.getTRIGGER_NAME());
        setTRIGGER_GROUP(from.getTRIGGER_GROUP());
        setBLOB_DATA(from.getBLOB_DATA());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IQrtzBlobTriggers> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QrtzBlobTriggersRecord
     */
    public QrtzBlobTriggersRecord() {
        super(QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS);
    }

    /**
     * Create a detached, initialised QrtzBlobTriggersRecord
     */
    public QrtzBlobTriggersRecord(String SCHED_NAME, String TRIGGER_NAME, String TRIGGER_GROUP, byte[] BLOB_DATA) {
        super(QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS);

        setSCHED_NAME(SCHED_NAME);
        setTRIGGER_NAME(TRIGGER_NAME);
        setTRIGGER_GROUP(TRIGGER_GROUP);
        setBLOB_DATA(BLOB_DATA);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised QrtzBlobTriggersRecord
     */
    public QrtzBlobTriggersRecord(org.fk.codegen.testshop.tables.dtos.QrtzBlobTriggers value) {
        super(QrtzBlobTriggers.QRTZ_BLOB_TRIGGERS);

        if (value != null) {
            setSCHED_NAME(value.getSCHED_NAME());
            setTRIGGER_NAME(value.getTRIGGER_NAME());
            setTRIGGER_GROUP(value.getTRIGGER_GROUP());
            setBLOB_DATA(value.getBLOB_DATA());
            resetChangedOnNotNull();
        }
    }
}
