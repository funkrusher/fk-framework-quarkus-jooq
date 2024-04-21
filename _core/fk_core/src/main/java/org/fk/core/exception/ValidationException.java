package org.fk.core.exception;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

/**
 * ValidationException
 *
 * <p>This exception typically represents errors related to input validation or domain-specific validation rules.
 * It is often thrown in the business logic layer (e.g., service or manager layer) when validating data
 * before performing an operation. These errors are usually not related to HTTP-specific concerns
 * and are better handled in lower layers.</p>
 *
 * <p>For our application those are: DAO, Manager</p>
 */
public class ValidationException extends MappingException {
    private Set<? extends ConstraintViolation<?>> violations;

    public ValidationException(String message) {
        super(message);
    }

    public <T> ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        super("");
        this.violations = violations;
    }

    public Set<? extends ConstraintViolation<?>> getViolations() {
        return violations;
    }
}