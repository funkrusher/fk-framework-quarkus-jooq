package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzBlobTriggers;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzBlobTriggersDto<T extends QrtzBlobTriggersDto> extends AbstractDTO implements IQrtzBlobTriggers {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private byte[] BLOB_DATA;


    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzBlobTriggersDto() {}

    public QrtzBlobTriggersDto(IQrtzBlobTriggers value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.SCHED_NAME</code>.
     */
    @Override
    public T setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_NAME() {
        return this.TRIGGER_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_NAME</code>.
     */
    @Override
    public T setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.keeper.touch("TRIGGER_NAME");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getTRIGGER_GROUP() {
        return this.TRIGGER_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.TRIGGER_GROUP</code>.
     */
    @Override
    public T setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.keeper.touch("TRIGGER_GROUP");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.QRTZ_BLOB_TRIGGERS.BLOB_DATA</code>.
     */
    @Size(max = 65535)
    @Override
    public byte[] getBLOB_DATA() {
        return this.BLOB_DATA;
    }

    /**
     * Setter for <code>testshop.QRTZ_BLOB_TRIGGERS.BLOB_DATA</code>.
     */
    @Override
    public T setBLOB_DATA(byte[] BLOB_DATA) {
        this.BLOB_DATA = BLOB_DATA;
        this.keeper.touch("BLOB_DATA");
        return (T) this;
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
    }

    @Override
    public <E extends IQrtzBlobTriggers> E into(E into) {
        into.from(this);
        return into;
    }

}
