package org.fk.core.dao.record;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.UserRole;
import org.fk.codegen.testshop.tables.interfaces.IUserRole;
import org.fk.codegen.testshop.tables.records.UserRoleRecord;
import org.fk.core.jooq.JooqContext;
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
