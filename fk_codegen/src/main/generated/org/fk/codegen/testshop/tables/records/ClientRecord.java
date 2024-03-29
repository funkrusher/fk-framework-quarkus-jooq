/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.records;


import org.fk.codegen.testshop.tables.Client;
import org.fk.codegen.testshop.tables.interfaces.IClient;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ClientRecord extends UpdatableRecordImpl<ClientRecord> implements IClient {

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
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IClient from) {
        setClientId(from.getClientId());
        resetChangedOnNotNull();
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
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ClientRecord
     */
    public ClientRecord(org.fk.codegen.testshop.tables.dtos.Client value) {
        super(Client.CLIENT);

        if (value != null) {
            setClientId(value.getClientId());
            resetChangedOnNotNull();
        }
    }
}
