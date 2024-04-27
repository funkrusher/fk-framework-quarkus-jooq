/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.pojos;
import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzBlobTriggers;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzBlobTriggersDto implements IQrtzBlobTriggers, DTO {

    private static final long serialVersionUID = 1L;

    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private byte[] BLOB_DATA;

    public QrtzBlobTriggersDto() { this.keeper = new BookKeeper(this); }


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
    public QrtzBlobTriggersDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("sCHED_NAME");
        return this;
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
    public QrtzBlobTriggersDto setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
        this.keeper.touch("tRIGGER_NAME");
        return this;
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
    public QrtzBlobTriggersDto setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
        this.keeper.touch("tRIGGER_GROUP");
        return this;
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
    public QrtzBlobTriggersDto setBLOB_DATA(byte[] BLOB_DATA) {
        this.BLOB_DATA = BLOB_DATA;
        this.keeper.touch("bLOB_DATA");
        return this;
    }

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
    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
    
    @JsonIgnore
    @XmlTransient
    protected BookKeeper keeper;
    
    @JsonIgnore
    @XmlTransient
    @Override
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}