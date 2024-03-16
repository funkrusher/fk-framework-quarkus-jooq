package org.fk.daos.record;

import org.fk.daos.AbstractRecordDAO;
import org.fk.generated.jooq_testshop.tables.UserRole;
import org.fk.generated.jooq_testshop.tables.interfaces.IUserRole;
import org.fk.generated.jooq_testshop.tables.records.UserRoleRecord;
import org.fk.jooq.JooqContext;
import org.jooq.Record2;

/**
 * UserRoleRecordDAO
 */
public class UserRoleRecordDAO extends AbstractRecordDAO<UserRoleRecord, IUserRole, Record2<Integer, String>> {

    public UserRoleRecordDAO(JooqContext jooqContext) {
        super(jooqContext, UserRole.USER_ROLE);
    }

    @Override
    public Record2<Integer, String> getId(UserRoleRecord object) {
        return compositeKeyRecord(object.getUserId(), object.getRoleId());
    }
}
