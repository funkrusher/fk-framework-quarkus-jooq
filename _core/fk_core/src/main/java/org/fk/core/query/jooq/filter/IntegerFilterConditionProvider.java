package org.fk.core.query.jooq.filter;

import org.fk.core.exception.InvalidDataException;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.impl.QOM;

import java.math.BigDecimal;
import java.util.List;

/**
 * IntegerFilterConditionProvider
 * <p>
 * Creates all supported Jooq {@link Condition}s for given String values.
 */
public class IntegerFilterConditionProvider implements FilterConditionProvider {

    private final Field<Integer> field;

    public IntegerFilterConditionProvider(final Field<Integer> field) {
        this.field = field;
    }

    private Integer getInteger(String value) throws InvalidDataException {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new InvalidDataException("unsupported integer format", e);
        }
    }

    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        return field.eq(getInteger(value));
    }

    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        return field.ne(getInteger(value));
    }

    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        return field.ge(getInteger(value));
    }

    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        return field.gt(getInteger(value));
    }

    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        return field.le(getInteger(value));
    }

    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        return field.lt(getInteger(value));
    }

    @Override
    public Condition inCondition(List<String> value) throws InvalidDataException {
        return field.in(value.stream().map(this::getInteger).toList());
    }
}
