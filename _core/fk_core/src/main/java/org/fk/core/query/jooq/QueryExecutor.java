package org.fk.core.query.jooq;

import org.fk.core.dto.DTO;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.model.FkFilter;
import org.fk.core.query.model.FkQuery;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class QueryExecutor<D extends DTO, T> {

    private final Field<?> idField;
    private final QueryFunction queryFunction;

    public QueryExecutor(Field<T> idField, QueryFunction queryFunction) {
        this.idField = idField;
        this.queryFunction = queryFunction;
    }

    /**
     * Paginate the given @{@link FkQuery} and return
     * a list of sorted, filtered, page-sized DTOs that match all criteria defined in the given query-parameters.
     * This will be a list that is only as large as the page-size defined in the given query-parameters.
     *
     * @return list of sorted, filtered, page-sized DTOs
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public String query(final FkQuery query) throws InvalidDataException {
        return queryFunction.apply(query)
            .fetch()
            .formatJSON(new JSONFormat().wrapSingleColumnRecords(false).header(false));
    }

    /**
     * Count the given @{@link FkFilter}s and return
     * the total count of all items that would match the filters of the given query-parameters
     *
     * @return total count of items, matching the given filters
     * @throws InvalidDataException invalid data in the given filters
     */
    public int count(final List<FkFilter> filters) throws InvalidDataException {
        final SelectFinalStep<Record1<JSON>> select = queryFunction.apply(new FkQuery().setFilters(filters));
        return Objects.requireNonNull(select.configuration()).dsl().fetchCount(select);
    }

    /**
     * Streams the given @{@link FkQuery} and return
     * a stream of sorted, filtered DTOs that match all criteria defined in the given query-parameters.
     *
     * @return stream of sorted, filtered Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public Stream<Object> stream(final FkQuery query) throws InvalidDataException {
        return queryFunction.apply(query)
            .fetchSize(250)
            .fetchStream()
            .map(x -> x.value1());
    }

    /**
     * Fetches the DTOs for the given Ids
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    public final String fetch(final List<T> ids) {
        final SelectFinalStep<Record1<JSON>> select = queryFunction.apply(new FkQuery());
        select.getQuery().addConditions(idField.in(ids));

        if (ids.size() == 1) {
            return select.fetchOne().formatJSON(new JSONFormat().wrapSingleColumnRecords(false).header(false));
        } else {
            return select.fetch().formatJSON(new JSONFormat().header(false));
        }
    }

    /**
     * Fetch DTO by ID.
     *
     * @param id The ID of a rec in the underlying table
     * @return A dto with all children resolved, given its ID, or
     * <code>null</code> if no dto was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    public @Nullable String fetch(final T id) {
        String result = fetch(singletonList(id));
        return result;
    }


    /**
     * Fetches the DTOs for the given Ids
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    @SafeVarargs
    public final String fetch(final T... ids) {
        return fetch(asList(ids));
    }
}
