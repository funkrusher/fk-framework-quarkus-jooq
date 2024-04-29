package org.fk.core.manager;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.core.dto.DTO;
import org.fk.core.exception.ValidationException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A common base-class for Managers
 * <p>
 * This type is implemented by Manager classes to provide the validation-api
 * and other helpful utils all managers may need.
 * </p>
 */
public abstract class AbstractManager {

    @Inject
    Validator validator;

    private void validateModifiedFields(DTO dto, Set<ConstraintViolation<DTO>> violations) {
        for (Map.Entry<String, Object> entry : dto.getBookKeeper().touched().entrySet()) {
            // we only validate the modified fields.
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof DTO) {
                // ignore, as we are not validating recursive.
            } else if (value instanceof List && !((List)value).isEmpty() && ((List)value).get(0) instanceof DTO) {
                // ignore, as we are not validating recursive.
            } else {
                Set<ConstraintViolation<DTO>> fieldViolations = validator.validateProperty((DTO) dto, key);
                violations.addAll(fieldViolations);
            }
        }
    }

    /**
     * validate the given DTO for insert.
     * - all fields are validated, even if they are not set.
     * - we do not validate sub-items / sub-lists, because the field-values are sometimes dependent on the parent,
     * and are only available after the parent has been inserted into the database (does not come from the frontend).
     *
     * @param dto dto
     * @throws ValidationException invalid dto
     */
    protected void validateInsert(DTO dto) throws ValidationException {
        Set<ConstraintViolation<DTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }

    /**
     * validate the given DTO for update.
     * - only validate fields in the modifiedFields, as in the update only those are considered as given.
     * - we do not validate sub-items / sub-lists because of the reasons mentioned in validateInsert.
     *
     * @param dto dto
     * @throws ValidationException invalid dto
     */
    protected void validateUpdate(DTO dto) throws ValidationException {
        Set<ConstraintViolation<DTO>> violations = new HashSet<>();
        validateModifiedFields(dto, violations);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }
}