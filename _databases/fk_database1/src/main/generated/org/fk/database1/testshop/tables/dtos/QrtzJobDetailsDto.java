package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IQrtzJobDetails;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzJobDetailsDto implements IQrtzJobDetails, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String SCHED_NAME;
    private String JOB_NAME;
    private String JOB_GROUP;
    private String DESCRIPTION;
    private String JOB_CLASS_NAME;
    private String IS_DURABLE;
    private String IS_NONCONCURRENT;
    private String IS_UPDATE_DATA;
    private String REQUESTS_RECOVERY;
    private byte[] JOB_DATA;


    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public QrtzJobDetailsDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.SCHED_NAME</code>.
     */
    @NotNull
    @Size(max = 120)
    @Override
    public String getSCHED_NAME() {
        return this.SCHED_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.SCHED_NAME</code>.
     */
    @Override
    public QrtzJobDetailsDto setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
        this.keeper.touch("SCHED_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.JOB_NAME</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getJOB_NAME() {
        return this.JOB_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.JOB_NAME</code>.
     */
    @Override
    public QrtzJobDetailsDto setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
        this.keeper.touch("JOB_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.JOB_GROUP</code>.
     */
    @NotNull
    @Size(max = 190)
    @Override
    public String getJOB_GROUP() {
        return this.JOB_GROUP;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.JOB_GROUP</code>.
     */
    @Override
    public QrtzJobDetailsDto setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
        this.keeper.touch("JOB_GROUP");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.DESCRIPTION</code>.
     */
    @Size(max = 250)
    @Override
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.DESCRIPTION</code>.
     */
    @Override
    public QrtzJobDetailsDto setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
        this.keeper.touch("DESCRIPTION");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.JOB_CLASS_NAME</code>.
     */
    @NotNull
    @Size(max = 250)
    @Override
    public String getJOB_CLASS_NAME() {
        return this.JOB_CLASS_NAME;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.JOB_CLASS_NAME</code>.
     */
    @Override
    public QrtzJobDetailsDto setJOB_CLASS_NAME(String JOB_CLASS_NAME) {
        this.JOB_CLASS_NAME = JOB_CLASS_NAME;
        this.keeper.touch("JOB_CLASS_NAME");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.IS_DURABLE</code>.
     */
    @NotNull
    @Size(max = 1)
    @Override
    public String getIS_DURABLE() {
        return this.IS_DURABLE;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.IS_DURABLE</code>.
     */
    @Override
    public QrtzJobDetailsDto setIS_DURABLE(String IS_DURABLE) {
        this.IS_DURABLE = IS_DURABLE;
        this.keeper.touch("IS_DURABLE");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.IS_NONCONCURRENT</code>.
     */
    @NotNull
    @Size(max = 1)
    @Override
    public String getIS_NONCONCURRENT() {
        return this.IS_NONCONCURRENT;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.IS_NONCONCURRENT</code>.
     */
    @Override
    public QrtzJobDetailsDto setIS_NONCONCURRENT(String IS_NONCONCURRENT) {
        this.IS_NONCONCURRENT = IS_NONCONCURRENT;
        this.keeper.touch("IS_NONCONCURRENT");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.IS_UPDATE_DATA</code>.
     */
    @NotNull
    @Size(max = 1)
    @Override
    public String getIS_UPDATE_DATA() {
        return this.IS_UPDATE_DATA;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.IS_UPDATE_DATA</code>.
     */
    @Override
    public QrtzJobDetailsDto setIS_UPDATE_DATA(String IS_UPDATE_DATA) {
        this.IS_UPDATE_DATA = IS_UPDATE_DATA;
        this.keeper.touch("IS_UPDATE_DATA");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.REQUESTS_RECOVERY</code>.
     */
    @NotNull
    @Size(max = 1)
    @Override
    public String getREQUESTS_RECOVERY() {
        return this.REQUESTS_RECOVERY;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.REQUESTS_RECOVERY</code>.
     */
    @Override
    public QrtzJobDetailsDto setREQUESTS_RECOVERY(String REQUESTS_RECOVERY) {
        this.REQUESTS_RECOVERY = REQUESTS_RECOVERY;
        this.keeper.touch("REQUESTS_RECOVERY");
        return this;
    }

    /**
     * Getter for <code>testshop.QRTZ_JOB_DETAILS.JOB_DATA</code>.
     */
    @Size(max = 65535)
    @Override
    public byte[] getJOB_DATA() {
        return this.JOB_DATA;
    }

    /**
     * Setter for <code>testshop.QRTZ_JOB_DETAILS.JOB_DATA</code>.
     */
    @Override
    public QrtzJobDetailsDto setJOB_DATA(byte[] JOB_DATA) {
        this.JOB_DATA = JOB_DATA;
        this.keeper.touch("JOB_DATA");
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
    public void from(IQrtzJobDetails from) {
        setSCHED_NAME(from.getSCHED_NAME());
        setJOB_NAME(from.getJOB_NAME());
        setJOB_GROUP(from.getJOB_GROUP());
        setDESCRIPTION(from.getDESCRIPTION());
        setJOB_CLASS_NAME(from.getJOB_CLASS_NAME());
        setIS_DURABLE(from.getIS_DURABLE());
        setIS_NONCONCURRENT(from.getIS_NONCONCURRENT());
        setIS_UPDATE_DATA(from.getIS_UPDATE_DATA());
        setREQUESTS_RECOVERY(from.getREQUESTS_RECOVERY());
        setJOB_DATA(from.getJOB_DATA());
    }
    @Override
    public <E extends IQrtzJobDetails> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
