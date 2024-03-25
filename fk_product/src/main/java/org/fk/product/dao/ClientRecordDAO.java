package org.fk.product.dao;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.Client;
import org.fk.codegen.testshop.tables.interfaces.IClient;
import org.fk.codegen.testshop.tables.records.ClientRecord;
import org.jooq.DSLContext;

/**
 * ClientRecordDAO
 */
public class ClientRecordDAO extends AbstractRecordDAO<ClientRecord, IClient, Integer> {

    public ClientRecordDAO(DSLContext dsl) {
        super(dsl, Client.CLIENT);
    }

    @Override
    public Integer getId(ClientRecord object) {
        return object.getClientId();
    }
}
