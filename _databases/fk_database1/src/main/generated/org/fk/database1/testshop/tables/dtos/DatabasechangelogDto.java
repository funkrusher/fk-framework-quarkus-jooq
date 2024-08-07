package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.interfaces.IDatabasechangelog;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangelogDto implements IDatabasechangelog, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private String ID;
    private String AUTHOR;
    private String FILENAME;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime DATEEXECUTED;
    private Integer ORDEREXECUTED;
    private String EXECTYPE;
    private String MD5SUM;
    private String DESCRIPTION;
    private String COMMENTS;
    private String TAG;
    private String LIQUIBASE;
    private String CONTEXTS;
    private String LABELS;
    private String DEPLOYMENT_ID;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public DatabasechangelogDto() {}

    public DatabasechangelogDto(IDatabasechangelog value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getID() {
        return this.ID;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @Override
    public DatabasechangelogDto setID(String ID) {
        this.ID = ID;
        this.keeper.touch("ID");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getAUTHOR() {
        return this.AUTHOR;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @Override
    public DatabasechangelogDto setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
        this.keeper.touch("AUTHOR");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getFILENAME() {
        return this.FILENAME;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @Override
    public DatabasechangelogDto setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
        this.keeper.touch("FILENAME");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @NotNull
    @Override
    public LocalDateTime getDATEEXECUTED() {
        return this.DATEEXECUTED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @Override
    public DatabasechangelogDto setDATEEXECUTED(LocalDateTime DATEEXECUTED) {
        this.DATEEXECUTED = DATEEXECUTED;
        this.keeper.touch("DATEEXECUTED");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @NotNull
    @Override
    public Integer getORDEREXECUTED() {
        return this.ORDEREXECUTED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @Override
    public DatabasechangelogDto setORDEREXECUTED(Integer ORDEREXECUTED) {
        this.ORDEREXECUTED = ORDEREXECUTED;
        this.keeper.touch("ORDEREXECUTED");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @NotNull
    @Size(max = 10)
    @Override
    public String getEXECTYPE() {
        return this.EXECTYPE;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @Override
    public DatabasechangelogDto setEXECTYPE(String EXECTYPE) {
        this.EXECTYPE = EXECTYPE;
        this.keeper.touch("EXECTYPE");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Size(max = 35)
    @Override
    public String getMD5SUM() {
        return this.MD5SUM;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Override
    public DatabasechangelogDto setMD5SUM(String MD5SUM) {
        this.MD5SUM = MD5SUM;
        this.keeper.touch("MD5SUM");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Size(max = 255)
    @Override
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Override
    public DatabasechangelogDto setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
        this.keeper.touch("DESCRIPTION");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCOMMENTS() {
        return this.COMMENTS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Override
    public DatabasechangelogDto setCOMMENTS(String COMMENTS) {
        this.COMMENTS = COMMENTS;
        this.keeper.touch("COMMENTS");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Size(max = 255)
    @Override
    public String getTAG() {
        return this.TAG;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Override
    public DatabasechangelogDto setTAG(String TAG) {
        this.TAG = TAG;
        this.keeper.touch("TAG");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Size(max = 20)
    @Override
    public String getLIQUIBASE() {
        return this.LIQUIBASE;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Override
    public DatabasechangelogDto setLIQUIBASE(String LIQUIBASE) {
        this.LIQUIBASE = LIQUIBASE;
        this.keeper.touch("LIQUIBASE");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCONTEXTS() {
        return this.CONTEXTS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Override
    public DatabasechangelogDto setCONTEXTS(String CONTEXTS) {
        this.CONTEXTS = CONTEXTS;
        this.keeper.touch("CONTEXTS");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Size(max = 255)
    @Override
    public String getLABELS() {
        return this.LABELS;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Override
    public DatabasechangelogDto setLABELS(String LABELS) {
        this.LABELS = LABELS;
        this.keeper.touch("LABELS");
        return this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Size(max = 10)
    @Override
    public String getDEPLOYMENT_ID() {
        return this.DEPLOYMENT_ID;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Override
    public DatabasechangelogDto setDEPLOYMENT_ID(String DEPLOYMENT_ID) {
        this.DEPLOYMENT_ID = DEPLOYMENT_ID;
        this.keeper.touch("DEPLOYMENT_ID");
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
    public void from(IDatabasechangelog from) {
        setID(from.getID());
        setAUTHOR(from.getAUTHOR());
        setFILENAME(from.getFILENAME());
        setDATEEXECUTED(from.getDATEEXECUTED());
        setORDEREXECUTED(from.getORDEREXECUTED());
        setEXECTYPE(from.getEXECTYPE());
        setMD5SUM(from.getMD5SUM());
        setDESCRIPTION(from.getDESCRIPTION());
        setCOMMENTS(from.getCOMMENTS());
        setTAG(from.getTAG());
        setLIQUIBASE(from.getLIQUIBASE());
        setCONTEXTS(from.getCONTEXTS());
        setLABELS(from.getLABELS());
        setDEPLOYMENT_ID(from.getDEPLOYMENT_ID());
    }

    @Override
    public <E extends IDatabasechangelog> E into(E into) {
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
