package org.fk.core.util.exception;

/**
 * ObjectNotFoundError
 *
 * <p>This exception is used when a requested resource or object cannot be found.
 * It's thrown when attempting to retrieve or manipulate data that doesn't exist in the system.
 * Object not found errors are typically more related to the core functionality of your application
 * and are appropriately handled in lower layers, such as the data access layer or service layer.</p>
 *
 * <p>For our application those are: DAO, Manager</p>
 */
public class ObjectNotFoundException extends MappingException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}