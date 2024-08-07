/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import org.fk.core.test.database.coretestdatabase.tables.Basic2;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic2;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Basic2Record extends UpdatableRecordImpl<Basic2Record> implements IBasic2 {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    @Override
    public Basic2Record setUuidId(UUID value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    @NotNull
    @Override
    public UUID getUuidId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    @Override
    public Basic2Record setString1(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    @Size(max = 50)
    @Override
    public String getString1() {
        return (String) get(1);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    @Override
    public Basic2Record setString2(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    @Size(max = 50)
    @Override
    public String getString2() {
        return (String) get(2);
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    @Override
    public Basic2Record setClientId(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IBasic2 from) {
        setUuidId(from.getUuidId());
        setString1(from.getString1());
        setString2(from.getString2());
        setClientId(from.getClientId());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IBasic2> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached Basic2Record
     */
    public Basic2Record() {
        super(Basic2.BASIC2);
    }

    /**
     * Create a detached, initialised Basic2Record
     */
    public Basic2Record(UUID uuidId, String string1, String string2, Integer clientId) {
        super(Basic2.BASIC2);

        setUuidId(uuidId);
        setString1(string1);
        setString2(string2);
        setClientId(clientId);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised Basic2Record
     */
    public Basic2Record(org.fk.core.test.database.coretestdatabase.tables.pojos.Basic2 value) {
        super(Basic2.BASIC2);

        if (value != null) {
            setUuidId(value.getUuidId());
            setString1(value.getString1());
            setString2(value.getString2());
            setClientId(value.getClientId());
            resetChangedOnNotNull();
        }
    }
}
