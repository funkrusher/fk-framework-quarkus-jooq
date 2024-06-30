package org.fk.core.query.jooq;

import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.filter.FilterConditionProvider;
import org.fk.core.query.jooq.filter.FilterConditionProviderFactory;
import org.fk.core.query.jooq.sorter.SortFieldFactory;
import org.fk.core.query.model.*;
import org.jooq.*;
import org.jooq.impl.DSL;

import java.util.*;

import static java.util.Arrays.asList;
import static org.jooq.impl.DSL.*;

/**
 * QueryJooqMapper
 * <p>
 * The QueryJooqMapper is a helper-class that you can be used in your {@link org.fk.core.repository.AbstractRepository}
 * implementation, to map the given {@link FkQuery} object to the fitting Jooq-Filter/Sorters,
 * so you can use them in the building of your jooq-query.
 * <p>
 * As the name states it maps the {@link FkQuery} object to Jooq-Usable Statements.
 */
public class FkQueryJooqMapper {
    private final FkQuery fkQuery;
    private final Table<?> defaultTable;
    private final List<Field<?>> mappableFields = new ArrayList<>();

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    public FkQueryJooqMapper(FkQuery fkQuery, Table<?> defaultTable) {
        this.fkQuery = fkQuery;
        this.defaultTable = defaultTable;
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    /**
     * Resolves the filter-condition-providers for the given fields.
     *
     * @param fields fields
     * @return condition-providers
     */
    private static Map<Name, FilterConditionProvider> resolveFilterConditionProviders(List<Field<?>> fields) {
        Map<Name, FilterConditionProvider> map = new HashMap<>();
        for (Field<?> field : fields) {
            map.put(field.getQualifiedName(), FilterConditionProviderFactory.create(field));
        }
        return map;
    }


    /**
     * Resolves the Jooq Name Identifier for a given QueryParamKey.
     *
     * @param queryParamKey queryParamKey
     * @return Name
     */
    private Name getNameForQueryParamKey(String queryParamKey) {
        final String[] tableAndField = queryParamKey.split("\\.");
        if (tableAndField.length > 1) {
            // we have table and field
            return DSL.name(tableAndField[0], tableAndField[1]);
        } else {
            // we only have field, use table() as default.
            return DSL.name(this.defaultTable.getName(), tableAndField[0]);
        }
    }


    // -------------------------------------------------------------------------
    // QueryJooqMapper API
    // -------------------------------------------------------------------------

    /**
     * Add mappable fields, that are supported for query to jooq mapping.
     *
     * @param mappableFields mappableFields
     * @return this
     */
    public FkQueryJooqMapper addMappableFields(List<Field<?>> mappableFields) {
        this.mappableFields.addAll(mappableFields);
        return this;
    }

    public FkQueryJooqMapper addMappableFields(Field<?>... mappableFields) {
        return addMappableFields(asList(mappableFields));
    }

    public FkQueryJooqMapper addMappableFields(Table<?> table) {
        return addMappableFields(table.fields());
    }

    /**
     * Maps the given @{@link FkSorter} contained in the queryParameters to Jooq @{@link SortField}s
     * for use in Jooq-Queries.
     * <p>
     * Note: this function returns a list for convenience reasons. It will always return 1 result, or empty list.
     *
     * @return list with one sort-field or empty-list
     * @throws InvalidDataException queryParameters contains invalid data.
     */
    public Collection<SortField<Object>> getSorter() throws InvalidDataException {
        if (fkQuery.getSorter() != null) {
            FkSorter sorter = fkQuery.getSorter();
            Name fieldName = getNameForQueryParamKey(sorter.getField());

            Optional<Field<?>> maybeField = mappableFields.stream().filter(x -> x.getQualifiedName().equals(fieldName)).findFirst();
            if (maybeField.isEmpty()) {
                throw new InvalidDataException("invalid sorter-field in query: " + sorter.getField());
            }
            return SortFieldFactory.create(maybeField.get(), sorter.getOperator());
        }
        return Collections.emptyList();
    }

    /**
     * Maps the given @{@link FkFilter}s contained in the queryParameters to Jooq @{@link Condition}s
     * for use in Jooq-Queries.
     *
     * @return filter-conditions
     * @throws InvalidDataException queryParameters contains invalid data.
     */
    public Collection<Condition> getFilters() throws InvalidDataException {
        Collection<Condition> filterFields = new ArrayList<>();
        final Map<Name, FilterConditionProvider> filterConditionProviders = resolveFilterConditionProviders(mappableFields);
        if (!fkQuery.getFilters().isEmpty()) {
            for (FkFilter filter : fkQuery.getFilters()) {
                Name fieldName = getNameForQueryParamKey(filter.getField());
                final FilterConditionProvider conditionProvider = filterConditionProviders.get(fieldName);
                if (conditionProvider == null) {
                    throw new InvalidDataException("invalid filter-field in query: " + filter.getField());
                }
                String value = filter.getValues().getFirst();
                if (filter.getOperator() == FkFilterOperator.EQUALS) {
                    filterFields.add(conditionProvider.eqCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.NOT_EQUALS) {
                    filterFields.add(conditionProvider.neCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.GREATER_THAN) {
                    filterFields.add(conditionProvider.gtCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.GREATER_THAN_OR_EQUALS) {
                    filterFields.add(conditionProvider.geCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.LESS_THAN) {
                    filterFields.add(conditionProvider.ltCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.LESS_THAN_OR_EQUALS) {
                    filterFields.add(conditionProvider.leCondition(value));
                } else if (filter.getOperator() == FkFilterOperator.IN) {
                    filterFields.add(conditionProvider.inCondition(filter.getValues()));
                }
            }
        }
        return filterFields;
    }

    public Field<Integer> getLimit() {
        if (fkQuery.getPageSize() != null) {
            return val(fkQuery.getPageSize());
        } else {
            return noField(Integer.class);
        }
    }

    public Field<Integer> getOffset() {
        if (fkQuery.getPageSize() != null) {
            return val(fkQuery.getPage());
        } else {
            return noField(Integer.class);
        }
    }
}
