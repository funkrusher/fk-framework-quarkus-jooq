package org.fk.core.query.jooq.filter;

import org.fk.core.exception.InvalidDataException;
import org.jooq.Condition;
import org.jooq.Field;

import java.util.List;

/**
 * StringFilterConditionProvider
 * <p>
 * Creates all supported Jooq {@link Condition}s for given String values.
 */
public class StringFilterConditionProvider implements FilterConditionProvider {

    private final Field<String> field;

    public StringFilterConditionProvider(final Field<String> field) {
        this.field = field;
    }

    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        return field.eq(value);
    }

    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        return field.ne(value);
    }

    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        throw new InvalidDataException("ge not applicable!");
    }

    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        throw new InvalidDataException("gt not applicable!");
    }

    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        throw new InvalidDataException("le not applicable!");
    }

    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        throw new InvalidDataException("lt not applicable!");
    }

    @Override
    public Condition inCondition(List<String> value) throws InvalidDataException {
        return field.in(value);
    }

}
