package org.fk.core.query.jooq;

import org.fk.core.dto.AbstractDTO;
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

public class QueryExecutor<D extends AbstractDTO, T> {

    private final Field<?> idField;
    private final QueryFunction<D> queryFunction;

    public QueryExecutor(Field<T> idField, QueryFunction<D> queryFunction) {
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
    public List<D> query(final FkQuery query) throws InvalidDataException {
        return queryFunction.apply(query)
            .fetch()
            .map(Record1::value1);
    }

    /**
     * Count the given @{@link FkFilter}s and return
     * the total count of all items that would match the filters of the given query-parameters
     *
     * @return total count of items, matching the given filters
     * @throws InvalidDataException invalid data in the given filters
     */
    public int count(final List<FkFilter> filters) throws InvalidDataException {
        final SelectFinalStep<Record1<D>> select = queryFunction.apply(new FkQuery().setFilters(filters));
        return Objects.requireNonNull(select.configuration()).dsl().fetchCount(select);
    }

    /**
     * Streams the given @{@link FkQuery} and return
     * a stream of sorted, filtered DTOs that match all criteria defined in the given query-parameters.
     *
     * @return stream of sorted, filtered Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public Stream<D> stream(final FkQuery query) throws InvalidDataException {
        return queryFunction.apply(query)
            .fetchSize(250)
            .fetchStream()
            .map(Record1::value1);
    }

    /**
     * Fetches the DTOs for the given Ids
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    public final List<D> fetch(final List<T> ids) {
        final SelectFinalStep<Record1<D>> select = queryFunction.apply(new FkQuery());
        select.getQuery().addConditions(idField.in(ids));
        return select.fetch().map(Record1::value1);

    }

    /**
     * Fetch DTO by ID.
     *
     * @param id The ID of a rec in the underlying table
     * @return A dto with all children resolved, given its ID, or
     * <code>null</code> if no dto was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    public @Nullable D fetch(final T id) {
        List<D> result = fetch(singletonList(id));
        if (!result.isEmpty()) {
            return result.getFirst();
        }
        return null;
    }


    /**
     * Fetches the DTOs for the given Ids
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    @SafeVarargs
    public final List<D> fetch(final T... ids) {
        return fetch(asList(ids));
    }
}
