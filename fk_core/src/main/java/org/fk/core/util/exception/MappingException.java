package org.fk.core.util.exception;

/**
 * MappingException
 *
 * <p>An abstract error type that represents issues related to the mapping of data from one form to another.
 * Mapping errors can occur when transforming data into a different structure or format due to various issues.</p>
 *
 * <p>Usage: MappingException serves as a generic error type for all mapping-related errors
 * and can be extended with specific error classes to cover different mapping-related scenarios.</p>
 */
public class MappingException extends Exception {
    public MappingException(String message) {
        super(message);
    }
}