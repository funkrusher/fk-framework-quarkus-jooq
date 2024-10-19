package org.fk.framework.query.jooq.filter;

import org.fk.framework.exception.InvalidDataException;
import org.jooq.Condition;
import org.jooq.Field;
import java.util.List;

/**
 * LongFilterConditionProvider
 * <p>
 * Creates all supported Jooq {@link Condition}s for given String values.
 */
public class LongFilterConditionProvider implements FilterConditionProvider {
    private final Field<Long> field;

    public LongFilterConditionProvider(final Field<Long> field) {
        this.field = field;
    }

    private Long getLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new InvalidDataException("unsupported long format", e);
        }
    }

    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        return field.eq(getLong(value));
    }

    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        return field.ne(getLong(value));
    }

    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        return field.ge(getLong(value));
    }

    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        return field.gt(getLong(value));
    }

    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        return field.le(getLong(value));
    }

    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        return field.lt(getLong(value));
    }

    @Override
    public Condition inCondition(List<String> value) throws InvalidDataException {
        return field.in(value.stream().map(this::getLong).toList());
    }
}
