package org.fk.core.query.jooq.filter;

import org.fk.core.exception.InvalidDataException;
import org.jooq.Condition;

/**
 * UnsupportedFilterConditionProvider
 * <p>
 * Throws for all given String values, because they are all not applicable
 */
public class UnsupportedFilterConditionProvider implements FilterConditionProvider {

    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("eq not applicable!");
    }

    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("ne not applicable!");
    }

    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("ge not applicable!");
    }

    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("gt not applicable!");
    }

    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("le not applicable!");
    }

    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        throw new UnsupportedOperationException("lt not applicable!");
    }
}
