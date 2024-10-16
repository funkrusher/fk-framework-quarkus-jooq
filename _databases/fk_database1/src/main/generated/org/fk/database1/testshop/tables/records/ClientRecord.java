/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import org.fk.database1.testshop.tables.Client;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ClientRecord extends UpdatableRecordImpl<ClientRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.client.clientId</code>.
     */
    public ClientRecord setClientid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.client.clientId</code>.
     */
    public Integer getClientid() {
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
    public ClientRecord(Integer clientid) {
        super(Client.CLIENT);

        setClientid(clientid);
        resetChangedOnNotNull();
    }
}
