package org.fk.database1.testshop.tables.dtos;

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

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private byte[] BLOB_DATA;


    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzBlobTriggersDto() {}

    public QrtzBlobTriggersDto(IQrtzBlobTriggers value) {
        this.setSCHED_NAME(value.getSCHED_NAME());
        this.setTRIGGER_NAME(value.getTRIGGER_NAME());
        this.setTRIGGER_GROUP(value.getTRIGGER_GROUP());
        this.setBLOB_DATA(value.getBLOB_DATA());
    }

    public QrtzBlobTriggersDto(
        String SCHED_NAME,
        String TRIGGER_NAME,
        String TRIGGER_GROUP,
        byte[] BLOB_DATA
    ) {
        this.setSCHED_NAME(SCHED_NAME);
        this.setTRIGGER_NAME(TRIGGER_NAME);
        this.setTRIGGER_GROUP(TRIGGER_GROUP);
        this.setBLOB_DATA(BLOB_DATA);
    }

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
    public QrtzBlobTriggersDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
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
        this.keeper.touch("TRIGGER_NAME");
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
        this.keeper.touch("TRIGGER_GROUP");
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
        this.keeper.touch("BLOB_DATA");
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
    protected transient BookKeeper keeper = new BookKeeper(this);
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
