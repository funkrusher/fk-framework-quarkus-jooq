package org.fk.core.query.jooq.filter;

import org.fk.core.exception.InvalidDataException;
import org.jooq.Condition;
import org.jooq.Field;

import java.math.BigDecimal;
import java.util.List;

/**
 * BigDecimalFilterConditionProvider
 * <p>
 * Creates all supported Jooq {@link Condition}s for given String values.
 */
public class BigDecimalFilterConditionProvider implements FilterConditionProvider {

    private final Field<BigDecimal> field;

    public BigDecimalFilterConditionProvider(final Field<BigDecimal> field) {
        this.field = field;
    }

    private BigDecimal getBigDecimal(String value) {
        try {
            return new BigDecimal(value);
        } catch (Exception e) {
            throw new InvalidDataException("unsupported big decimal format", e);
        }
    }

    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        return field.eq(getBigDecimal(value));
    }

    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        return field.ne(getBigDecimal(value));
    }

    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        return field.ge(getBigDecimal(value));
    }

    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        return field.gt(getBigDecimal(value));
    }

    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        return field.le(getBigDecimal(value));
    }

    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        return field.lt(getBigDecimal(value));
    }

    @Override
    public Condition inCondition(List<String> value) throws InvalidDataException {
        return field.in(value.stream().map(this::getBigDecimal).toList());
    }
}
