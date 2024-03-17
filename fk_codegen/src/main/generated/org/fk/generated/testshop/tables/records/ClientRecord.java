/*
 * This file is generated by jOOQ.
 */
package org.fk.generated.testshop.tables.records;


import jakarta.validation.Valid;

import org.fk.generated.testshop.tables.Client;
import org.fk.generated.testshop.tables.interfaces.IClient;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Valid
public class ClientRecord extends UpdatableRecordImpl<ClientRecord> implements Record1<Integer>, IClient {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.client.clientId</code>.
     */
    @Override
    public void setClientId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>testshop.client.clientId</code>.
     */
    @Override
    public Integer getClientId() {
        return (Integer) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<Integer> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<Integer> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Client.CLIENT.CLIENTID;
    }

    @Override
    public Integer component1() {
        return getClientId();
    }

    @Override
    public Integer value1() {
        return getClientId();
    }

    @Override
    public ClientRecord value1(Integer value) {
        setClientId(value);
        return this;
    }

    @Override
    public ClientRecord values(Integer value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IClient from) {
        setClientId(from.getClientId());
    }

    @Override
    public <E extends IClient> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClientRecord
     */
    public ClientRecord() {
        super(Client.CLIENT);
    }

    /**
     * Create a detached, initialised ClientRecord
     */
    public ClientRecord(Integer clientId) {
        super(Client.CLIENT);

        setClientId(clientId);
    }

    /**
     * Create a detached, initialised ClientRecord
     */
    public ClientRecord(org.fk.generated.testshop.tables.dtos.Client value) {
        super(Client.CLIENT);

        if (value != null) {
            setClientId(value.getClientId());
        }
    }
}
