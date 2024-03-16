package org.fk.util.exception;

/**
 * NoSuchObjectException
 * <p>
 * we want to enforce, that the caller catches the NoSuchObjectException and handles it accordingly.
 * </p>
 */
public class NoSuchObjectException extends Exception {
    public NoSuchObjectException(String message) {
        super(message);
    }
}