package org.fk.product.dto.old;


import org.fk.database1.testshop.tables.dtos.UserRoleDto;
import org.fk.database1.testshop.tables.interfaces.IUserRole;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.jooq.Record1;

/**
 * UserRoleDTO
 */
public class UserRoleDTO extends UserRoleDto<UserRoleDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public UserRoleDTO() {
    }

    public UserRoleDTO(IUserRole value) {
        this.from(value);
    }

    public static UserRoleDTO create(Record1<UserRoleRecord> r) {
        return new UserRoleDTO(r.value1());
    }
}