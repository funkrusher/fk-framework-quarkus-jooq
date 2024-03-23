package org.fk.core.dao.record;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.User;
import org.fk.codegen.testshop.tables.interfaces.IUser;
import org.fk.codegen.testshop.tables.records.UserRecord;
import org.fk.core.jooq.JooqContext;

/**
 * UserRecordDAO
 */
public class UserRecordDAO extends AbstractRecordDAO<UserRecord, IUser, Integer> {

    public UserRecordDAO(JooqContext jooqContext) {
        super(jooqContext, User.USER);
    }

    @Override
    public Integer getId(UserRecord object) {
        return object.getUserId();
    }
}
