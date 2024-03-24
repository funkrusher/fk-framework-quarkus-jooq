/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.codegen.testshop.tables.Databasechangelog;
import org.fk.codegen.testshop.tables.interfaces.IDatabasechangelog;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangelogRecord extends TableRecordImpl<DatabasechangelogRecord> implements IDatabasechangelog {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @Override
    public void setID(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ID</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getID() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @Override
    public void setAUTHOR(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.AUTHOR</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getAUTHOR() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @Override
    public void setFILENAME(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.FILENAME</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getFILENAME() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @Override
    public void setDATEEXECUTED(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @NotNull
    @Override
    public LocalDateTime getDATEEXECUTED() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @Override
    public void setORDEREXECUTED(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @NotNull
    @Override
    public Integer getORDEREXECUTED() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @Override
    public void setEXECTYPE(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @NotNull
    @Size(max = 10)
    @Override
    public String getEXECTYPE() {
        return (String) get(5);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Override
    public void setMD5SUM(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Size(max = 35)
    @Override
    public String getMD5SUM() {
        return (String) get(6);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Override
    public void setDESCRIPTION(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Size(max = 255)
    @Override
    public String getDESCRIPTION() {
        return (String) get(7);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Override
    public void setCOMMENTS(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCOMMENTS() {
        return (String) get(8);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Override
    public void setTAG(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.TAG</code>.
     */
    @Size(max = 255)
    @Override
    public String getTAG() {
        return (String) get(9);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Override
    public void setLIQUIBASE(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Size(max = 20)
    @Override
    public String getLIQUIBASE() {
        return (String) get(10);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Override
    public void setCONTEXTS(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Size(max = 255)
    @Override
    public String getCONTEXTS() {
        return (String) get(11);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Override
    public void setLABELS(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.LABELS</code>.
     */
    @Size(max = 255)
    @Override
    public String getLABELS() {
        return (String) get(12);
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Override
    public void setDEPLOYMENT_ID(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Size(max = 10)
    @Override
    public String getDEPLOYMENT_ID() {
        return (String) get(13);
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
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IDatabasechangelog> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DatabasechangelogRecord
     */
    public DatabasechangelogRecord() {
        super(Databasechangelog.DATABASECHANGELOG);
    }

    /**
     * Create a detached, initialised DatabasechangelogRecord
     */
    public DatabasechangelogRecord(String ID, String AUTHOR, String FILENAME, LocalDateTime DATEEXECUTED, Integer ORDEREXECUTED, String EXECTYPE, String MD5SUM, String DESCRIPTION, String COMMENTS, String TAG, String LIQUIBASE, String CONTEXTS, String LABELS, String DEPLOYMENT_ID) {
        super(Databasechangelog.DATABASECHANGELOG);

        setID(ID);
        setAUTHOR(AUTHOR);
        setFILENAME(FILENAME);
        setDATEEXECUTED(DATEEXECUTED);
        setORDEREXECUTED(ORDEREXECUTED);
        setEXECTYPE(EXECTYPE);
        setMD5SUM(MD5SUM);
        setDESCRIPTION(DESCRIPTION);
        setCOMMENTS(COMMENTS);
        setTAG(TAG);
        setLIQUIBASE(LIQUIBASE);
        setCONTEXTS(CONTEXTS);
        setLABELS(LABELS);
        setDEPLOYMENT_ID(DEPLOYMENT_ID);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DatabasechangelogRecord
     */
    public DatabasechangelogRecord(org.fk.codegen.testshop.tables.dtos.Databasechangelog value) {
        super(Databasechangelog.DATABASECHANGELOG);

        if (value != null) {
            setID(value.getID());
            setAUTHOR(value.getAUTHOR());
            setFILENAME(value.getFILENAME());
            setDATEEXECUTED(value.getDATEEXECUTED());
            setORDEREXECUTED(value.getORDEREXECUTED());
            setEXECTYPE(value.getEXECTYPE());
            setMD5SUM(value.getMD5SUM());
            setDESCRIPTION(value.getDESCRIPTION());
            setCOMMENTS(value.getCOMMENTS());
            setTAG(value.getTAG());
            setLIQUIBASE(value.getLIQUIBASE());
            setCONTEXTS(value.getCONTEXTS());
            setLABELS(value.getLABELS());
            setDEPLOYMENT_ID(value.getDEPLOYMENT_ID());
            resetChangedOnNotNull();
        }
    }
}