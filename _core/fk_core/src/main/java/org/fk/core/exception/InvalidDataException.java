package org.fk.core.exception;

/**
 * InvalidDataException
 *
 * <p>A specific mapping error that occurs when the data being processed is invalid
 * or does not conform to the expected format or constraints.
 * This error indicates that the data cannot be correctly transformed into the desired form.</p>
 *
 * <p>Usage: InvalidDataException is used when there are issues with the quality or validity of the data,
 * making it unsuitable for mapping.</p>
 *
 * <p>
 * Related HTTP-Status-Code: 400
 * </p>
 */
public class InvalidDataException extends MappingException {
    public InvalidDataException(String message) {
        super(message);
    }
}