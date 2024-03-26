package org.fk.core.repository;

import jakarta.validation.constraints.NotNull;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.*;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A common base-class for Repositories
 * <p>
 * This type is implemented by Repository classes.
 *
 * A repository will be responsible to provide paginate, stream and query operations on a specific joined-view and also
 * map the resulting database records to the DTO representing this view on the related tables that are part of this view.
 * Such a view on related tables is often producing a 'Kartesisches Produkt' of N->M relationships in its result,
 * and therefore the mapping to the DTO must take this into account.
 * </p>
 */
public abstract class AbstractRepository {

    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    /**
     * Define all fields in the different tables that are part of this view, and should be resolved
     * into the DTOs that this view is returning.
     *
     * @return fields
     */
    abstract public List<Field<?>> getViewFields();

    /**
     * Define all joins on the different tables that are part of this view, and should be resolved
     * into the DTOs that this view is returning.
     *
     * @return joins
     */
    abstract public TableOnConditionStep<Record> getViewJoins();





    private Name getNameForQueryParamKey(String queryParamKey, Table<?> defaultTable) {
        final String[] tableAndField = queryParamKey.split("\\.");
        if (tableAndField.length > 1) {
            // we have table and field
            return DSL.name(tableAndField[0], tableAndField[1]);
        } else {
            // we only have field, use table() as default.
            return DSL.name(defaultTable.getName(), tableAndField[0]);
        }
    }

    private Field<?> findViewFieldByName(Name name) {
        for (Field<?> viewField : getViewFields()) {
            Name viewFieldName = viewField.getQualifiedName();
            if (viewFieldName.equals(name)) {
                return viewField;
            }
        }
        return null;
    }


    protected Collection<SortField<?>> getSorters(QueryParameters queryParameters, Table<?> defaultTable) throws InvalidDataException {
        Collection<SortField<?>> sortFields = new ArrayList<>();
        if (queryParameters.getSorter() != null) {
            Sorter sorter = queryParameters.getSorter();
            Name fieldName = getNameForQueryParamKey(sorter.getField(), defaultTable);
            Field<?> field = findViewFieldByName(fieldName);
            if (field == null) {
                throw new InvalidDataException("invalid sorter-field in query: " + sorter.getField());
            }
            if (sorter.getOperator() == SorterOperator.ASC) {
                sortFields.add(field.sort(SortOrder.ASC));
            } else if (sorter.getOperator() == SorterOperator.DESC) {
                sortFields.add(field.sort(SortOrder.DESC));
            }
        }
        return sortFields;
    }

    protected Collection<Condition> getFilters(QueryParameters queryParameters, Table<?> defaultTable) throws InvalidDataException {
        Collection<Condition> filterFields = new ArrayList<>();
        if (!queryParameters.getFilters().isEmpty()) {
            for (Filter filter : queryParameters.getFilters()) {
                Name fieldName = getNameForQueryParamKey(filter.getField(), defaultTable);
                Field<?> field = findViewFieldByName(fieldName);
                if (field == null) {
                    throw new InvalidDataException("invalid filter-field in query: " + filter.getField());
                }

                final Class<?> type = field.getType();
                if (String.class.isAssignableFrom(type)) {
                    String value = filter.getValues().getFirst();
                    Field<String> field0 = (Field<String>) field;
                    filterFields.add(field0.eq(value));
                } else if (Integer.class.isAssignableFrom(type)) {
                    Integer value = Integer.parseInt(filter.getValues().getFirst());
                    Field<Integer> field0 = (Field<Integer>) field;
                    filterFields.add(field0.eq(value));
                } else if (Long.class.isAssignableFrom(type)) {
                    Long value = Long.parseLong(filter.getValues().getFirst());
                    Field<Long> field0 = (Field<Long>) field;
                    if (filter.getOperator() == FilterOperator.EQUALS) {
                        filterFields.add(field0.eq(value));
                    } else if (filter.getOperator() == FilterOperator.GREATER_THAN_OR_EQUALS) {
                        filterFields.add(field0.ge(value));
                    } else if (filter.getOperator() == FilterOperator.LESS_THAN_OR_EQUALS) {
                        filterFields.add(field0.le(value));
                    }
                } else if (BigDecimal.class.isAssignableFrom(type)) {
                    BigDecimal value = new BigDecimal(filter.getValues().getFirst());
                    Field<BigDecimal> field0 = (Field<BigDecimal>) field;
                    if (filter.getOperator() == FilterOperator.EQUALS) {
                        filterFields.add(field0.eq(value));
                    } else if (filter.getOperator() == FilterOperator.GREATER_THAN_OR_EQUALS) {
                        filterFields.add(field0.ge(value));
                    }
                }
            }
        }
        return filterFields;
    }



}