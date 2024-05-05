package org.fk.core.query.jooq.filter;

import org.fk.core.query.model.FkFilter;
import org.jooq.Condition;

/**
 * FilterConditionProvider
 * <p>
 * Can be implemented for each Java Object Type, to map a given {@link FkFilter}
 * and its value, to a @{@link Condition}
 */
public interface FilterConditionProvider {
    public Condition eqCondition(String value);

    public Condition geCondition(String value);

    public Condition leCondition(String value);
}
