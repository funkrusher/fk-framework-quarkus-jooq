package org.fk.core.repository;

import org.fk.core.dto.DTO;
import org.fk.core.query.model.*;
import org.fk.core.request.RequestContext;
import org.fk.core.exception.InvalidDataException;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.fk.core.request.RequestContext.DSL_DATA_KEY;
import static org.jooq.impl.DSL.key;

/**
 * A common base-class for Repositories
 * <p>
 * This type is implemented by Repository classes.
 * <p>
 * A repository will be responsible to provide paginate, stream and query operations on a specific joined-view and also
 * map the resulting database records to the DTO representing this view on the related tables that are part of this view.
 * Such a view on related tables is often producing a 'Kartesisches Produkt' of N->M relationships in its result,
 * and therefore the mapping to the DTO must take this into account.
 * </p>
 */
public abstract class AbstractRepository<D extends DTO, T> {
    private final DSLContext dsl;
    private final Class<D> dtoClazz;
    private final Field<T> idField;
    private final RequestContext request;

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractRepository(DSLContext dsl, Class<D> dtoClazz, Field<T> idField) {
        this.dsl = dsl;
        this.dtoClazz = dtoClazz;
        this.idField = idField;
        this.request = (RequestContext) dsl.data(DSL_DATA_KEY);
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    /**
     * Sometimes we need to use jsonObject in queries, and jooq currently has no helper-function
     * to convert fields to the expected json-entries. We provide it here for subclasses to use
     * (maybe move this to a utility-class). See:
     * - https://stackoverflow.com/questions/70458821/jooq-jsonobject-select-all-statement
     *
     * @param fields fields
     * @return json-entries.
     */
    protected JSONEntry<?>[] asJsonEntries(Field<?>[] fields) {
        return Arrays.stream(fields).map(f -> key(f.getName()).value(f)).toArray(JSONEntry[]::new);
    }

    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    protected abstract SelectFinalStep<Record> prepareQuery(FkQuery query) throws InvalidDataException;

    /**
     * You can override this method if specific result mappings are needed / different from the default.
     * But only do this, if you really need it.
     *
     * @param dto dto
     * @return dto
     */
    protected D mapResult(D dto) {
        return dto;
    }

    // -------------------------------------------------------------------------
    // Repository API
    // -------------------------------------------------------------------------

    /**
     * Expose the dslContext this <code>DAO</code> is operating.
     *
     * @return the <code>DAO</code>'s underlying <code>dslContext</code>
     */
    public DSLContext dsl() {
        return this.dsl;
    }

    /**
     * Expose the request this <code>DAO</code> is operating.
     *
     * @return the <code>DAO</code>'s underlying <code>request</code>
     */
    public RequestContext request() {
        return this.request;
    }

    /**
     * Paginate the given @{@link FkQuery} and return
     * a list of sorted, filtered, page-sized DTOs that match all criteria defined in the given query-parameters.
     * This will be a list that is only as large as the page-size defined in the given query-parameters.
     *
     * @param query query
     * @return list of sorted, filtered, page-sized DTOs
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public List<D> query(FkQuery query) throws InvalidDataException {
        return prepareQuery(query)
                .fetchInto(dtoClazz)
                .stream().map(this::mapResult).toList();
    }

    /**
     * Count the given @{@link FkFilter}s and return
     * the total count of all items that would match the filters of the given query-parameters
     *
     * @param filters filters
     * @return total count of items, matching the given filters
     * @throws InvalidDataException invalid data in the given filters
     */
    public int count(@Nullable List<FkFilter> filters) throws InvalidDataException {
        return dsl().fetchCount(prepareQuery(new FkQuery().setFilters(filters == null ? new ArrayList<>() : filters)));
    }

    /**
     * Streams the given @{@link FkQuery} and return
     * a stream of sorted, filtered DTOs that match all criteria defined in the given query-parameters.
     *
     * @param query query
     * @return stream of sorted, filtered Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public Stream<D> stream(FkQuery query) throws InvalidDataException {
        return prepareQuery(query)
                .fetchSize(250)
                .fetchStreamInto(dtoClazz)
                .map(this::mapResult);
    }

    /**
     * Fetches the DTOs for the given Ids
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    public final List<D> fetch(final List<T> ids) {
        final FkFilter filter = new FkFilter(idField.getName(), FkFilterOperator.IN, ids.stream().map(Object::toString).toList());
        return query(new FkQuery().setFilters(List.of(filter)));
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