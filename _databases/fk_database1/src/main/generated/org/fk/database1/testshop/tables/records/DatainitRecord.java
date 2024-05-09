/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.Datainit;
import org.fk.database1.testshop.tables.interfaces.IDatainit;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatainitRecord extends TableRecordImpl<DatainitRecord> implements IDatainit {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.DataInit.dataInitId</code>.
     */
    @Override
    public DatainitRecord setDataInitId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DataInit.dataInitId</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getDataInitId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>testshop.DataInit.createdAt</code>.
     */
    @Override
    public DatainitRecord setCreatedAt(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.DataInit.createdAt</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(1);
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IDatainit from) {
        setDataInitId(from.getDataInitId());
        setCreatedAt(from.getCreatedAt());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IDatainit> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DatainitRecord
     */
    public DatainitRecord() {
        super(Datainit.DATAINIT);
    }

    /**
     * Create a detached, initialised DatainitRecord
     */
    public DatainitRecord(String dataInitId, LocalDateTime createdAt) {
        super(Datainit.DATAINIT);

        setDataInitId(dataInitId);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised DatainitRecord
     */
    public DatainitRecord(org.fk.database1.testshop.tables.pojos.Datainit value) {
        super(Datainit.DATAINIT);

        if (value != null) {
            setDataInitId(value.getDataInitId());
            setCreatedAt(value.getCreatedAt());
            resetChangedOnNotNull();
        }
    }
}