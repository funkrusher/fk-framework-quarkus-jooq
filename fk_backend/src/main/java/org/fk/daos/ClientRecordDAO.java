package org.fk.daos;

import org.fk.dao.AbstractRecordDAO;
import org.fk.generated.jooq_testshop.tables.Client;
import org.fk.generated.jooq_testshop.tables.interfaces.IClient;
import org.fk.generated.jooq_testshop.tables.records.ClientRecord;
import org.fk.jooq.JooqContext;

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
