package org.fk.core.util.exception;

/**
 * DatabaseException
 *
 * <p>Exceptions related to the database or infrastructure, like DatabaseException,
 * are typically caught and handled in lower layers, as these layers are closer to the source of the error.
 * Handling them allows you to log and potentially take corrective actions, such as retries.</p>
 *
 * <p>This error can occur only in the Manager-Layer as it's the only layer executing the database-transactions.</p>
 */
public class DatabaseException extends MappingException {
    public DatabaseException(String message) {
        super(message);
    }
}