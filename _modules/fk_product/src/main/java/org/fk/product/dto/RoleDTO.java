package org.fk.product.dto;


import org.fk.database1.testshop.tables.dtos.RoleDto;
import org.fk.database1.testshop.tables.dtos.UserRoleDto;
import org.fk.database1.testshop.tables.interfaces.IRole;
import org.fk.database1.testshop.tables.interfaces.IUserRole;

/**
 * RoleDto
 */
public class RoleDTO extends RoleDto implements IRole {

    public RoleDTO() {
        super();
    }
}
