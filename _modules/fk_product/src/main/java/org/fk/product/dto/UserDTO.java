package org.fk.product.dto;


import org.fk.database1.testshop.tables.interfaces.IUser;
import org.fk.database1.testshop.tables.dtos.UserDto;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDTO
 */
public class UserDTO extends UserDto implements IUser {

    public UserDTO() {
        super();
    }


    private List<RoleDTO> roles = new ArrayList<RoleDTO>();

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<RoleDTO> roles) {
        this.roles = roles;
        keeper.touch("roles");
        return this;
    }
}
