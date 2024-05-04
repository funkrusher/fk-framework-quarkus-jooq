package org.fk.core.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

/**
 * ValidationException
 *
 * <p>This exception typically represents errors related to input validation or domain-specific validation rules.
 * It is often thrown in the business logic layer (e.g., service or manager layer) when validating data
 * before performing an operation.</p>
 * <p>
 * Related HTTP-Status-Code: 422
 * </p>
 */
public class ValidationException extends MappingException {
    private final transient Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(String message) {
        super(message);
        this.violations = null;
    }

    public <T> ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super("");
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}