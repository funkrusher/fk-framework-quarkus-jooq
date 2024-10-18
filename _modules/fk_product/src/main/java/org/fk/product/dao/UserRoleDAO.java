package org.fk.product.dao;

import org.fk.core.dao.AbstractDAO;
import org.fk.database1.testshop.tables.UserRole;
import org.fk.database1.testshop.tables.interfaces.IUserRole;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.jooq.DSLContext;
import org.jooq.Record2;

/**
 * UserRoleDAO
 */
public class UserRoleDAO extends AbstractDAO<UserRoleRecord, Record2<Integer, String>> {

    public UserRoleDAO(DSLContext dsl) {
        super(dsl, UserRole.USER_ROLE);
    }
}
