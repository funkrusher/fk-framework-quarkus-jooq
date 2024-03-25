package org.fk.product.dao;

import org.fk.core.dao.AbstractRecordDAO;
import org.fk.codegen.testshop.tables.UserRole;
import org.fk.codegen.testshop.tables.interfaces.IUserRole;
import org.fk.codegen.testshop.tables.records.UserRoleRecord;
import org.jooq.DSLContext;
import org.jooq.Record2;

/**
 * UserRoleRecordDAO
 */
public class UserRoleRecordDAO extends AbstractRecordDAO<UserRoleRecord, IUserRole, Record2<Integer, String>> {

    public UserRoleRecordDAO(DSLContext dsl) {
        super(dsl, UserRole.USER_ROLE);
    }

    @Override
    public Record2<Integer, String> getId(UserRoleRecord object) {
        return compositeKeyRecord(object.getUserId(), object.getRoleId());
    }
}
