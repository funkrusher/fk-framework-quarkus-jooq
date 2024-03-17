package org.fk.dtos;


import jakarta.validation.Valid;
import org.fk.generated.testshop.tables.dtos.UserRole;
import org.fk.generated.testshop.tables.interfaces.IUserRole;

/**
 * UserRoleDTO
 */
@Valid
public class UserRoleDTO extends UserRole implements IUserRole {
}
