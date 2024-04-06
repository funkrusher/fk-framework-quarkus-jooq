package org.fk.product.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database1.testshop.tables.Client;
import org.fk.database1.testshop.tables.interfaces.IClient;
import org.fk.database1.testshop.tables.records.ClientRecord;
import org.jooq.DSLContext;

/**
 * ClientRecordDAO
 */
public class ClientDAO extends AbstractDAO<ClientRecord, IClient, Integer> {

    public ClientDAO(DSLContext dsl) {
        super(dsl, Client.CLIENT);
    }

    @Override
    public Integer getId(ClientRecord object) {
        return object.getClientId();
    }
}
