package org.fk.dtos;


import jakarta.validation.Valid;
import org.fk.generated.jooq_testshop.tables.dtos.User;
import org.fk.generated.jooq_testshop.tables.interfaces.IUser;

/**
 * UserDTO
 */
@Valid
public class UserDTO extends User implements IUser {
}
