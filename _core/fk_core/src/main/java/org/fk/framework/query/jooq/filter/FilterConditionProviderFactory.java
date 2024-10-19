package org.fk.framework.query.jooq.filter;

import org.fk.framework.exception.MappingException;
import org.jooq.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * FilterConditionProviderFactory
 * <p>
 * Creates @{@link FilterConditionProvider} instances for the given @{@link Field}.
 * Picks the correct provider for the given field-type.
 */
public class FilterConditionProviderFactory {

    private  FilterConditionProviderFactory() {
    }

    /**
     * Create @{@link FilterConditionProvider} for the given @{@link Field}
     * Picks the correct provider for the given field-type.
     *
     * @param field field
     * @return filter-condition-provider
     */
    public static FilterConditionProvider create(Field<?> field) throws MappingException {
        final Class<?> type = field.getType();
        if (String.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<String> field0 = (Field<String>) field;
            return new StringFilterConditionProvider(field0);
        } else if (Integer.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<Integer> field0 = (Field<Integer>) field;
            return new IntegerFilterConditionProvider(field0);
        } else if (Long.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<Long> field0 = (Field<Long>) field;
            return new LongFilterConditionProvider(field0);
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<BigDecimal> field0 = (Field<BigDecimal>) field;
            return new BigDecimalFilterConditionProvider(field0);
        } else if (LocalDateTime.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<LocalDateTime> field0 = (Field<LocalDateTime>) field;
            return new LocalDateTimeFilterConditionProvider(field0);
        } else {
            return new UnsupportedFilterConditionProvider();
        }
    }
}
