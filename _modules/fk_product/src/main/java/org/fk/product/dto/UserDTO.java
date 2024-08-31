package org.fk.product.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.dtos.UserDto;
import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record2;
import org.jooq.Record3;

import javax.annotation.Nullable;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDTO
 */
public class UserDTO extends UserDto {

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