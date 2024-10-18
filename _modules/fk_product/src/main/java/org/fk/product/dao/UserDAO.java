package org.fk.product.dao;

import org.fk.database1.testshop.tables.User;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.fk.core.dao.AbstractDAO;

/**
 * UserDAO
 */
public class UserDAO extends AbstractDAO<UserRecord, Integer> {

    public UserDAO(DSLContext dsl) {
        super(dsl, User.USER);
    }
}
