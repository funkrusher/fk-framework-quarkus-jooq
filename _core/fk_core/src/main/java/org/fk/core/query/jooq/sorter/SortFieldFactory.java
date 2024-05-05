package org.fk.core.query.jooq.sorter;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkSorterOperator;
import org.jooq.Field;
import org.jooq.SortField;
import org.jooq.SortOrder;
import java.util.List;

/**
 * SortFieldFactory
 * <p>
 * Create @{@link org.jooq.SortField} for the given @{@link Field} and @{@link FkSorterOperator}
 * Picks the correct sorter condition for the given parameters.
 */
public class SortFieldFactory {

    private  SortFieldFactory() {
    }

    /**
     * Create @{@link org.jooq.SortField} for the given @{@link Field} and @{@link FkSorterOperator}
     * Picks the correct sorter condition for the given parameters.
     *
     * @param field field
     * @param operator operator
     * @return filter-condition-provider
     */
    public static List<SortField<Object>> create(Field<?> field, FkSorterOperator operator) throws InvalidDataException {
        final Class<?> type = field.getType();
        if (Object.class.isAssignableFrom(type)) {
            @SuppressWarnings("unchecked")
            Field<Object> field0 = (Field<Object>) field;
            if (operator == FkSorterOperator.ASC) {
                return List.of(field0.sort(SortOrder.ASC));
            } else if (operator == FkSorterOperator.DESC) {
                return List.of(field0.sort(SortOrder.DESC));
            } else {
                throw new InvalidDataException("unsupported sorter-operator given for sorter! " + operator.getSymbol());
            }
        } else {
            // this should never be the case, because all '?' wildcards should be subclasses of Object.
            throw new InvalidDataException("unsupported type given for sorter! " + type.getName());
        }
    }
}
