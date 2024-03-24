package org.fk.core.dto;


import jakarta.validation.Valid;
import org.fk.codegen.testshop.tables.dtos.User;
import org.fk.codegen.testshop.tables.interfaces.IUser;

/**
 * UserDTO
 */
public class UserDTO extends User implements IUser {
}
