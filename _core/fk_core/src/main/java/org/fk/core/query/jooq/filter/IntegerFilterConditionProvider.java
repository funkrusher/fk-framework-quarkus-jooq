package org.fk.core.query.jooq.filter;

import org.jooq.Condition;
import org.jooq.Field;

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

    @Override
    public Condition eqCondition(String value) {
        return field.eq(Integer.parseInt(value));
    }

    @Override
    public Condition geCondition(String value) {
        return field.ge(Integer.parseInt(value));
    }

    @Override
    public Condition leCondition(String value) {
        return field.le(Integer.parseInt(value));
    }

}
