package org.fk.core.dao.record;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.Client;
import org.fk.codegen.testshop.tables.interfaces.IClient;
import org.fk.codegen.testshop.tables.records.ClientRecord;
import org.fk.core.jooq.JooqContext;

/**
 * ClientRecordDAO
 */
public class ClientRecordDAO extends AbstractRecordDAO<ClientRecord, IClient, Integer> {

    public ClientRecordDAO(JooqContext jooqContext) {
        super(jooqContext, Client.CLIENT);
    }

    @Override
    public Integer getId(ClientRecord object) {
        return object.getClientId();
    }
}
