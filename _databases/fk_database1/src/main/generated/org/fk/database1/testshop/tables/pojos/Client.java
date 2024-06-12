/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.pojos;


import org.fk.database1.testshop.tables.interfaces.IClient;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Client implements IClient {

    private static final long serialVersionUID = 1L;

    private Integer clientId;

    public Client() {}

    public Client(IClient value) {
        this.clientId = value.getClientId();
    }

    public Client(
        Integer clientId
    ) {
        this.clientId = clientId;
    }

    /**
     * Getter for <code>testshop.client.clientId</code>.
     */
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>testshop.client.clientId</code>.
     */
    @Override
    public Client setClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client (");

        sb.append(clientId);

        sb.append(")");
        return sb.toString();
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
}
