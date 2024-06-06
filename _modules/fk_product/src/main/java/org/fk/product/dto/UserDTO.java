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


    private List<UserRoleDTO> roles = new ArrayList<UserRoleDTO>();

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public UserDTO setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
        keeper.touch("roles");
        return this;
    }
}
