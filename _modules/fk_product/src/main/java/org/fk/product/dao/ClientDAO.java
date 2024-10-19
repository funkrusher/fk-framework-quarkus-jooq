package org.fk.product.dao;

import org.fk.framework.dao.AbstractDAO;
import org.fk.database1.testshop.tables.Client;
import org.fk.database1.testshop.tables.records.ClientRecord;
import org.jooq.DSLContext;

/**
 * ClientDAO
 */
public class ClientDAO extends AbstractDAO<ClientRecord, Integer> {

    public ClientDAO(DSLContext dsl) {
        super(dsl, Client.CLIENT);
    }
}
