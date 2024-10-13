package org.fk.product.dto.old;


import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record1;

/**
 * UserDTO
 */
public class UserDTO extends UserDto<UserDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public UserDTO() {
    }

    public UserDTO(IUser value) {
        this.from(value);
    }

    public static UserDTO create(Record1<UserRecord> r) {
        return new UserDTO(r.value1());
    }
}