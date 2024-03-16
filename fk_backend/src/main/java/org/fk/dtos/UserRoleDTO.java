package org.fk.dtos;


import jakarta.validation.Valid;
import org.fk.generated.jooq_testshop.tables.dtos.UserRole;
import org.fk.generated.jooq_testshop.tables.interfaces.IUserRole;

/**
 * UserRoleDTO
 */
@Valid
public class UserRoleDTO extends UserRole implements IUserRole {
}
