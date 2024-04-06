package org.fk.product.dto;


import jakarta.validation.Valid;
import org.fk.database1.testshop.tables.dtos.User;
import org.fk.database1.testshop.tables.interfaces.IUser;

/**
 * UserDTO
 */
public class UserDTO extends User implements IUser {
}
