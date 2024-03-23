package org.fk.core.dto;


import jakarta.validation.Valid;
import org.fk.codegen.testshop.tables.dtos.UserRole;
import org.fk.codegen.testshop.tables.interfaces.IUserRole;

/**
 * UserRoleDTO
 */
@Valid
public class UserRoleDTO extends UserRole implements IUserRole {
}
