package org.fk.core.manager;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.fk.core.dto.AbstractDTO;
import org.fk.core.exception.ValidationException;

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
    Validator validator;

    /**
     * validate the given DTO.
     * - all fields are validated, even if they are not set.
     * - we do not validate sub-items / sub-lists, because the field-values are sometimes dependent on the parent,
     * and are only available after the parent has been inserted into the database (does not come from the frontend).
     *
     * @param dto dto
     * @throws ValidationException invalid dto
     */
    protected void validate(AbstractDTO dto) throws ValidationException {
        Set<ConstraintViolation<AbstractDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }
    }

    /**
     * Helper function to chunk a stream of items into a stream of List of items.
     *
     * @param stream stream
     * @param size chunk-size of each chunk
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