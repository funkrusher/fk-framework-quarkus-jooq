package org.fk.product.dto;


import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDTO
 */
public class UserDTO extends UserDto<UserDTO> {

    private List<RoleDTO> roles = new ArrayList<RoleDTO>();

    public UserDTO() {}

    public UserDTO(IUser value) { this.from(value); }

    public static @Nullable UserDTO createOrNull(Record2<UserRecord, List<RoleDTO>> r) {
        if (r.value1().getUserId() == null) {
            return null;
        } else {
            return new UserDTO(r.value1())
                .setRoles(r.value2());
        }
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        keeper.touch("roles");
        return this;
    }
}