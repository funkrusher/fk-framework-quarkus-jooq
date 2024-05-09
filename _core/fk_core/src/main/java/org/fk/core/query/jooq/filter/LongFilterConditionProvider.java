package org.fk.core.query.jooq.filter;

import org.jooq.Condition;
import org.jooq.Field;

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

    @Override
    public Condition eqCondition(String value) {
        return field.eq(Long.parseLong(value));
    }

    @Override
    public Condition neCondition(String value) {
        return field.ne(Long.parseLong(value));
    }

    @Override
    public Condition geCondition(String value) {
        return field.ge(Long.parseLong(value));
    }

    @Override
    public Condition gtCondition(String value) {
        return field.gt(Long.parseLong(value));
    }

    @Override
    public Condition leCondition(String value) {
        return field.le(Long.parseLong(value));
    }

    @Override
    public Condition ltCondition(String value) {
        return field.lt(Long.parseLong(value));
    }
}
