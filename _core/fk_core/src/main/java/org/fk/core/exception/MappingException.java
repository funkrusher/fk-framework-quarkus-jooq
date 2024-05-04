package org.fk.core.exception;

/**
 * MappingException
 *
 * <p>An abstract error type that represents issues related to the mapping of data from one form to another.
 * Mapping errors can occur when transforming data into a different structure or format due to various issues.</p>
 *
 * <p>Usage: MappingException serves as a generic error type for all mapping-related errors
 * and can be extended with specific error classes to cover different mapping-related scenarios.</p>
 *
 * <p>
 * Related HTTP-Status-Code: 500
 * </p>
 */
public class MappingException extends RuntimeException {
    public MappingException() { super(); }
    public MappingException(String message) {
        super(message);
    }
    public MappingException(Exception e) {
        super(e);
    }
    public MappingException(String message, Exception e) {
        super(message, e);
    }
}