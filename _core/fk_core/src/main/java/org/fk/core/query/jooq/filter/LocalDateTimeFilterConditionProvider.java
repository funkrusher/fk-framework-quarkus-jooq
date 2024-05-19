package org.fk.core.query.jooq.filter;

import org.fk.core.exception.InvalidDataException;
import org.jooq.Condition;
import org.jooq.Field;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * LocalDateTimeFilterConditionProvider
 * <p>
 * Creates all supported Jooq {@link Condition}s for given LocalDateTime values.
 */
public class LocalDateTimeFilterConditionProvider implements FilterConditionProvider {

    private final Field<LocalDateTime> field;

    public LocalDateTimeFilterConditionProvider(final Field<LocalDateTime> field) {
        this.field = field;
    }

    private LocalDateTime getLocalDateTime(String value) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(value)), ZoneOffset.UTC);
        } catch (Exception e) {
            throw new InvalidDataException("unsupported local date time format", e);
        }
    }
    @Override
    public Condition eqCondition(String value) throws InvalidDataException {
        return field.eq(getLocalDateTime(value));
    }
    @Override
    public Condition neCondition(String value) throws InvalidDataException {
        return field.ne(getLocalDateTime(value));
    }
    @Override
    public Condition geCondition(String value) throws InvalidDataException {
        return field.ge(getLocalDateTime(value));
    }
    @Override
    public Condition gtCondition(String value) throws InvalidDataException {
        return field.gt(getLocalDateTime(value));
    }
    @Override
    public Condition leCondition(String value) throws InvalidDataException {
        return field.le(getLocalDateTime(value));
    }
    @Override
    public Condition ltCondition(String value) throws InvalidDataException {
        return field.lt(getLocalDateTime(value));
    }

    @Override
    public Condition inCondition(List<String> value) throws InvalidDataException {
        return field.in(value.stream().map(this::getLocalDateTime).toList());
    }
}
