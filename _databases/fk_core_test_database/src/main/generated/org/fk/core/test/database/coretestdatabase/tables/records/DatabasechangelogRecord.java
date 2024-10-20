/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.core.test.database.coretestdatabase.tables.Databasechangelog;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangelogRecord extends TableRecordImpl<DatabasechangelogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.ID</code>.
     */
    public DatabasechangelogRecord setId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.ID</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.AUTHOR</code>.
     */
    public DatabasechangelogRecord setAuthor(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.AUTHOR</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getAuthor() {
        return (String) get(1);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.FILENAME</code>.
     */
    public DatabasechangelogRecord setFilename(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.FILENAME</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getFilename() {
        return (String) get(2);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    public DatabasechangelogRecord setDateexecuted(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DATEEXECUTED</code>.
     */
    @NotNull
    public LocalDateTime getDateexecuted() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    public DatabasechangelogRecord setOrderexecuted(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.ORDEREXECUTED</code>.
     */
    @NotNull
    public Integer getOrderexecuted() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.EXECTYPE</code>.
     */
    public DatabasechangelogRecord setExectype(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.EXECTYPE</code>.
     */
    @NotNull
    @Size(max = 10)
    public String getExectype() {
        return (String) get(5);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.MD5SUM</code>.
     */
    public DatabasechangelogRecord setMd5sum(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.MD5SUM</code>.
     */
    @Size(max = 35)
    public String getMd5sum() {
        return (String) get(6);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    public DatabasechangelogRecord setDescription(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DESCRIPTION</code>.
     */
    @Size(max = 255)
    public String getDescription() {
        return (String) get(7);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.COMMENTS</code>.
     */
    public DatabasechangelogRecord setComments(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.COMMENTS</code>.
     */
    @Size(max = 255)
    public String getComments() {
        return (String) get(8);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.TAG</code>.
     */
    public DatabasechangelogRecord setTag(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.TAG</code>.
     */
    @Size(max = 255)
    public String getTag() {
        return (String) get(9);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    public DatabasechangelogRecord setLiquibase(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.LIQUIBASE</code>.
     */
    @Size(max = 20)
    public String getLiquibase() {
        return (String) get(10);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.CONTEXTS</code>.
     */
    public DatabasechangelogRecord setContexts(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.CONTEXTS</code>.
     */
    @Size(max = 255)
    public String getContexts() {
        return (String) get(11);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.LABELS</code>.
     */
    public DatabasechangelogRecord setLabels(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.LABELS</code>.
     */
    @Size(max = 255)
    public String getLabels() {
        return (String) get(12);
    }

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    public DatabasechangelogRecord setDeploymentId(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOG.DEPLOYMENT_ID</code>.
     */
    @Size(max = 10)
    public String getDeploymentId() {
        return (String) get(13);
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
    public DatabasechangelogRecord(String id, String author, String filename, LocalDateTime dateexecuted, Integer orderexecuted, String exectype, String md5sum, String description, String comments, String tag, String liquibase, String contexts, String labels, String deploymentId) {
        super(Databasechangelog.DATABASECHANGELOG);

        setId(id);
        setAuthor(author);
        setFilename(filename);
        setDateexecuted(dateexecuted);
        setOrderexecuted(orderexecuted);
        setExectype(exectype);
        setMd5sum(md5sum);
        setDescription(description);
        setComments(comments);
        setTag(tag);
        setLiquibase(liquibase);
        setContexts(contexts);
        setLabels(labels);
        setDeploymentId(deploymentId);
        resetChangedOnNotNull();
    }
}
