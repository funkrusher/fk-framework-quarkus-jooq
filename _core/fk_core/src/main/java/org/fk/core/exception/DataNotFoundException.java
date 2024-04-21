package org.fk.core.exception;

/**
 * DataNotFoundException
 *
 * <p>A specific mapping error that occurs when the required data cannot be found or retrieved.
 * This error typically indicates that the data necessary for the mapping operation is missing or unavailable.</p>
 *
 * <p>Usage: DataNotFoundError is used when an expected piece of data is not present, preventing successful mapping.</p>
 */
public class DataNotFoundException extends MappingException {
    public DataNotFoundException(String message) {
        super(message);
    }
}