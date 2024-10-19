package org.fk.framework.query.jooq.filter;

import org.fk.framework.exception.InvalidDataException;
import org.fk.framework.query.model.FkFilter;
import org.jooq.Condition;

import java.util.List;

/**
 * FilterConditionProvider
 * <p>
 * Can be implemented for each Java Object Type, to map a given {@link FkFilter}
 * and its value, to a @{@link Condition}
 */
public interface FilterConditionProvider {
    public Condition eqCondition(String value) throws InvalidDataException;

    public Condition neCondition(String value) throws InvalidDataException;

    public Condition geCondition(String value) throws InvalidDataException;

    public Condition gtCondition(String value) throws InvalidDataException;

    public Condition leCondition(String value) throws InvalidDataException;

    public Condition ltCondition(String value) throws InvalidDataException;

    public Condition inCondition(List<String> value) throws InvalidDataException;
}
