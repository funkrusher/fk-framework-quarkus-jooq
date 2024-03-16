package org.fk.daos;

import org.fk.dao.AbstractRecordDAO;
import org.fk.generated.jooq_testshop.tables.User;
import org.fk.generated.jooq_testshop.tables.interfaces.IUser;
import org.fk.generated.jooq_testshop.tables.records.UserRecord;
import org.fk.jooq.JooqContext;

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
