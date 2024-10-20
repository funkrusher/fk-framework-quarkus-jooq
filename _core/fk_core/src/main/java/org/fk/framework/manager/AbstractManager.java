package org.fk.framework.manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.framework.exception.ValidationException;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A common base-class for Managers
 * <p>
 * This type is implemented by Manager classes to provide the validation-api
 * and other helpful utils all managers may need.
 * </p>
 */
public abstract class AbstractManager {

    @Inject
    protected Validator validator;

    @Inject
    protected ObjectMapper objectMapper;

    /**
     * validate the given Patch against the given dto-clazz.
     * - only the fields in the patch are validated.
     *
     * @param patch    patch
     * @param dtoClazz dtoClazz
     * @throws ValidationException invalid dto
     */
    protected <T> T validatePatch(Map<String, Object> patch, Class<T> dtoClazz) throws ValidationException {
        final T dto = objectMapper.convertValue(patch, dtoClazz);

        // Validate the dto (but only for the fields present in the given patch)
        Set<ConstraintViolation<Object>> allViolations = new HashSet<>();
        for (Map.Entry<String, Object> entry : patch.entrySet()) {
            Set<ConstraintViolation<Object>> violations = this.validator.validateProperty(dto, entry.getKey());
            allViolations.addAll(violations);
        }
        if (!allViolations.isEmpty()) {
            throw new ValidationException(allViolations);
        }
        return dto;
    }

    /**
     * validate the given DTO.
     * - all fields are validated, even if they are not set.
     * - we do not validate sub-items / sub-lists, because the field-values are sometimes dependent on the parent,
     * and are only available after the parent has been inserted into the database (does not come from the frontend).
     *
     * @param dto dto
     * @throws ValidationException invalid dto
     */
    protected Object validate(Object dto) throws ValidationException {
        Set<ConstraintViolation<Object>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
        return dto;
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size   chunk-size of each chunk
     * @return stream of list of items.
     */
    protected <T> Stream<List<T>> chunk(Stream<T> stream, int size) {
        Iterator<T> iterator = stream.iterator();
        Iterator<List<T>> listIterator = new Iterator<>() {

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public List<T> next() {
                List<T> result = new ArrayList<>(size);
                for (int i = 0; i < size && iterator.hasNext(); i++) {
                    result.add(iterator.next());
                }
                return result;
            }
        };
        return StreamSupport.stream(((Iterable<List<T>>) () -> listIterator).spliterator(), false);
    }
}