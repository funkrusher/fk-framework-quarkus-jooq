package org.fk.framework.exception;

/**
 * NotFoundException
 *
 * <p>This exception is used when a requested resource or object cannot be found.
 * It's thrown when attempting to retrieve or manipulate data that doesn't exist in the system.
 * In most cases this is used to throw
 * </p>
 * <p>
 * Related HTTP-Status-Code: 404
 * </p>
 */
public class NotFoundException extends MappingException {

    public NotFoundException() {
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }
}