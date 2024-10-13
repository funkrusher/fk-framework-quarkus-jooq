package org.fk.product.dto.old;


import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * NestedUserDTO
 */
public class NestedUserDTO extends UserDto<NestedUserDTO> {

    private List<RoleDTO> roles = new ArrayList<RoleDTO>();

    public NestedUserDTO() {}

    public NestedUserDTO(IUser value) { this.from(value); }

    public static @Nullable NestedUserDTO createOrNull(Record2<UserRecord, List<RoleDTO>> r) {
        if (r.value1().getUserId() == null) {
            return null;
        } else {
            return new NestedUserDTO(r.value1())
                .setRoles(r.value2());
        }
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public NestedUserDTO setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        return this;
    }
}