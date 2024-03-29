package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database.testshop.tables.dtos.User;
import org.fk.database.testshop.tables.interfaces.IUser;

/**
 * UserDTO
 */
public class UserDTO extends User implements IUser {
}
