package org.fk.core.query.jooq.filter;

import org.jooq.Condition;
import org.jooq.Field;

import java.math.BigDecimal;

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

    @Override
    public Condition eqCondition(String value) {
        return field.eq(new BigDecimal(value));
    }

    @Override
    public Condition neCondition(String value) {
        return field.ne(new BigDecimal(value));
    }

    @Override
    public Condition geCondition(String value) {
        return field.ge(new BigDecimal(value));
    }

    @Override
    public Condition gtCondition(String value) {
        return field.gt(new BigDecimal(value));
    }

    @Override
    public Condition leCondition(String value) {
        return field.le(new BigDecimal(value));
    }

    @Override
    public Condition ltCondition(String value) {
        return field.lt(new BigDecimal(value));
    }
}
