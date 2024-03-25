package org.fk.product.dao;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.User;
import org.fk.codegen.testshop.tables.interfaces.IUser;
import org.fk.codegen.testshop.tables.records.UserRecord;
import org.jooq.DSLContext;

/**
 * UserRecordDAO
 */
public class UserRecordDAO extends AbstractRecordDAO<UserRecord, IUser, Integer> {

    public UserRecordDAO(DSLContext dsl) {
        super(dsl, User.USER);
    }

    @Override
    public Integer getId(UserRecord object) {
        return object.getUserId();
    }
}
