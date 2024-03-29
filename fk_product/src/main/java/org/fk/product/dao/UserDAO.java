package org.fk.product.dao;

import org.fk.database.testshop.tables.User;
import org.fk.database.testshop.tables.interfaces.IUser;
import org.fk.database.testshop.tables.records.UserRecord;
import org.jooq.DSLContext;
import org.fk.core.dao.AbstractDAO;

/**
 * UserRecordDAO
 */
public class UserDAO extends AbstractDAO<UserRecord, IUser, Integer> {

    public UserDAO(DSLContext dsl) {
        super(dsl, User.USER);
    }

    @Override
    public Integer getId(UserRecord object) {
        return object.getUserId();
    }
}
