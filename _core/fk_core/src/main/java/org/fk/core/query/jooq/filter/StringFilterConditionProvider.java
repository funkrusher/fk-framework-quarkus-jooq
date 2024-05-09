package org.fk.core.query.jooq.filter;

import org.jooq.Condition;
import org.jooq.Field;

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
    public Condition eqCondition(String value) {
        return field.eq(value);
    }

    @Override
    public Condition neCondition(String value) {
        return field.ne(value);
    }

    @Override
    public Condition geCondition(String value) {
        throw new UnsupportedOperationException("ge not applicable!");
    }

    @Override
    public Condition gtCondition(String value) {
        throw new UnsupportedOperationException("gt not applicable!");
    }

    @Override
    public Condition leCondition(String value) {
        throw new UnsupportedOperationException("le not applicable!");
    }

    @Override
    public Condition ltCondition(String value) {
        throw new UnsupportedOperationException("lt not applicable!");
    }

}
