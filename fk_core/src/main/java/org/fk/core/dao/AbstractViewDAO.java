package org.fk.core.dao;

import jakarta.validation.constraints.NotNull;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.*;
import org.fk.codegen.dto.AbstractDTO;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.*;

/**
 * A common base-class for read-only ViewDAOs
 * <p>
 * This type is implemented by DAO classes to provide a common API for common actions on a joined/paginated view
 * and returns DTOs that are mappable to this view.
 * </p>
 * @param <R> The generic record type.
 * @param <P> The generic DTO type.
 * @param <T> The generic primary key type. This is a regular
 *            <code>&lt;T&gt;</code> type for single-column keys, or a
 *            {@link Record} subtype for composite keys.
 */
public abstract class AbstractViewDAO<R extends UpdatableRecord<R>, P extends AbstractDTO, T> extends AbstractBaseDAO<R, T> {

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractViewDAO(DSLContext dsl, Table<R> table) {
        super(dsl, table);
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    private SelectJoinStep<Record> getViewQuery() {
        // prepare our view-query with the fields/joins of the subclass.
        return dsl()
                .select(getViewFields())
                .from(getViewJoins());
    }

    private Name getNameForQueryParamKey(@NotNull String queryParamKey) {
        final String[] tableAndField = queryParamKey.split("\\.");
        if (tableAndField.length > 1) {
            // we have table and field
            return DSL.name(tableAndField[0], tableAndField[1]);
        } else {
            // we only have field, use table() as default.
            return DSL.name(table().getName(), tableAndField[0]);
        }
    }

    private Field<?> findViewFieldByName(@NotNull Name name) {
        for (Field<?> viewField : getViewFields()) {
            Name viewFieldName = viewField.getQualifiedName();
            if (viewFieldName.equals(name)) {
                return viewField;
            }
        }
        return null;
    }


    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    /**
     * Define all fields in the different tables that are part of this view, and should be resolved
     * into the DTOs that this view is returning.
     *
     * @return fields
     */
    abstract protected List<Field<?>> getViewFields();

    /**
     * Define all joins on the different tables that are part of this view, and should be resolved
     * into the DTOs that this view is returning.
     *
     * @return joins
     */
    abstract protected TableOnConditionStep<Record> getViewJoins();


    // -------------------------------------------------------------------------
    // DAO API
    // -------------------------------------------------------------------------

    /**
     * Find a fully resolved DTO for this view by ID.
     *
     * @param id The ID of a record in the underlying table
     * @return A view-resolved DTO for the record of the underlying table given its ID, or
     * <code>null</code> if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    public Result<Record> findById(T id) throws DataAccessException {
        Field<?>[] pk = pk();
        if (pk != null) {
            Result<Record> result = getViewQuery()
                    .where(equal(pk, id))
                    .fetch();
            if (!result.isEmpty()) {
                return result;
            }
        }
        return null;
    }

    /**
     * Find a fully resolved DTO for this view by ID.
     *
     * @param id The ID of a record in the underlying table
     * @return A view-resolved DTO for the record of the underlying table given its ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public Optional<Result<Record>> findOptionalById(T id) throws DataAccessException {
        return Optional.ofNullable(findById(id));
    }

    /**
     * Performs a paginated query for this view
     *
     * @param queryParameters The paginated query
     * @return list of view-resolved DTOs
     * @throws DataAccessException if something went wrong executing the query
     */
    public Result<Record> query(final QueryParameters queryParameters) throws InvalidDataException, DataAccessException {
        Collection<SortField<?>> sortFields = new ArrayList<>();
        if (queryParameters.getSorter() != null) {
            Sorter sorter = queryParameters.getSorter();
            Name fieldName = getNameForQueryParamKey(sorter.getField());
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

        // dynamic sql, see: https://blog.jooq.org/a-functional-programming-approach-to-dynamic-sql-with-jooq/
        Collection<Condition> filterFields = new ArrayList<>();
        if (!queryParameters.getFilters().isEmpty()) {
            for (Filter filter : queryParameters.getFilters()) {
                Name fieldName = getNameForQueryParamKey(filter.getField());
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

        // Remote-Paginate Query that copes with the typical OFFSET/LIMIT trick for joined tables.
        //
        // 1. join all tables for filtering but only select the grouped-ids of the main-table where the filters
        // have matched, so we can make sure that the OFFSET/LIMIT is working correctly.
        //
        // 2. select all full joined entries for the found grouped-ids of the main-table (those may be more entries
        // than the LIMIT given, but this is ok then as they are combined in the upper layer.
        //
        var query = dsl()
                .select(pk())
                .from(getViewJoins())
                .where(DSL.and(filterFields))
                .groupBy(pk())
                .orderBy(sortFields)
                .offset(queryParameters.getPage())
                .limit(queryParameters.getPageSize());

        var records = query.fetchInto(table());
        List<T> ids = new ArrayList<T>();
        for (R record : records) {
            T id = getId(record);
            ids.add(id);
        }
        Field<?>[] pk = pk();
        var result = getViewQuery().where(equal(pk, ids)).fetch();
        return result;
    }
}
