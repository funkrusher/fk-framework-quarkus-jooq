package org.fk.core.manager;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.codegen.dto.AbstractDTO;
import org.fk.core.dto.ProductDTO;
import org.fk.core.jooq.JooqContext;
import org.fk.core.util.exception.ValidationException;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.UpdatableRecord;

import java.util.Set;

/**
 * A common base-class for Managers
 * <p>
 * This type is implemented by Manager classes to provide the validation-api
 * and other helpful utils all managers may need.
 * </p>
 */
public abstract class AbstractBaseManager {

    @Inject
    Validator validator;

    protected void validateDTO(AbstractDTO dto) throws ValidationException {
        Set<ConstraintViolation<AbstractDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}