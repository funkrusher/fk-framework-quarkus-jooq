package org.fk.product.dto;


import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.ITask;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.TaskRecord;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record1;
import org.jooq.Record2;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

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