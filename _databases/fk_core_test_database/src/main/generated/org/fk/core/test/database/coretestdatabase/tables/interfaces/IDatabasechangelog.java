/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IDatabasechangelog extends Serializable {

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.ID</code>.
     */
    public IDatabasechangelog setID(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.ID</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getID();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.AUTHOR</code>.
     */
    public IDatabasechangelog setAUTHOR(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.AUTHOR</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getAUTHOR();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.FILENAME</code>.
     */
    public IDatabasechangelog setFILENAME(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.FILENAME</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getFILENAME();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    public IDatabasechangelog setDATEEXECUTED(LocalDateTime value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @NotNull
    public LocalDateTime getDATEEXECUTED();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    public IDatabasechangelog setORDEREXECUTED(Integer value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @NotNull
    public Integer getORDEREXECUTED();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.EXECTYPE</code>.
     */
    public IDatabasechangelog setEXECTYPE(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @NotNull
    @Size(max = 10)
    public String getEXECTYPE();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.MD5SUM</code>.
     */
    public IDatabasechangelog setMD5SUM(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Size(max = 35)
    public String getMD5SUM();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    public IDatabasechangelog setDESCRIPTION(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Size(max = 255)
    public String getDESCRIPTION();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.COMMENTS</code>.
     */
    public IDatabasechangelog setCOMMENTS(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Size(max = 255)
    public String getCOMMENTS();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.TAG</code>.
     */
    public IDatabasechangelog setTAG(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.TAG</code>.
     */
    @Size(max = 255)
    public String getTAG();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    public IDatabasechangelog setLIQUIBASE(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Size(max = 20)
    public String getLIQUIBASE();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.CONTEXTS</code>.
     */
    public IDatabasechangelog setCONTEXTS(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Size(max = 255)
    public String getCONTEXTS();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.LABELS</code>.
     */
    public IDatabasechangelog setLABELS(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.LABELS</code>.
     */
    @Size(max = 255)
    public String getLABELS();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    public IDatabasechangelog setDEPLOYMENT_ID(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Size(max = 10)
    public String getDEPLOYMENT_ID();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IDatabasechangelog
     */
    public void from(IDatabasechangelog from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IDatabasechangelog
     */
    public <E extends IDatabasechangelog> E into(E into);
}
