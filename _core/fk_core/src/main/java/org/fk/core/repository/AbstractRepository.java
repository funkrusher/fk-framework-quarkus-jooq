package org.fk.core.repository;

import jakarta.annotation.Nullable;
import org.fk.core.dto.DTO;
import org.fk.core.query.model.*;
import org.fk.core.request.RequestContext;
import org.fk.core.exception.InvalidDataException;
import org.jooq.*;
import org.jooq.exception.DataAccessException;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.fk.core.request.RequestContext.DSL_DATA_KEY;

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
    private final RequestContext request;

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractRepository(DSLContext dsl) {
        this.dsl = dsl;
        this.request = (RequestContext) dsl.data(DSL_DATA_KEY);
    }

    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    /**
     * <p>
     * Maps the given @{@link FkQuery} to an execution-ready jooq-query
     * that processes the Tables-Relationship defined in the subclass.
     * </p>
     *
     * @param query query
     * @return execution-ready jooq-query
     * @throws InvalidDataException invalid data in the given queryParameters
     *
     */
    protected abstract SelectSeekStepN<Record1<T>> mapQuery(FkQuery query) throws InvalidDataException;

    /**
     * Paginate the given @{@link FkQuery} and return
     * a list of sorted, filtered, page-sized Ids that match all criteria defined in the given query-parameters.
     * This will be a list that is only as large as the page-size defined in the given query-parameters.
     *
     * @param query query
     * @return list of sorted, filtered, page-sized Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public abstract List<T> paginateQuery(FkQuery query) throws InvalidDataException;

    /**
     * Count the given @{@link FkQuery} and return
     * the total count of all items that would match the filters of the given query-parameters
     * (ignoring any page-size criteria, as we return the total count.
     *
     * @param query query
     * @return total count of items, matching the filters of the given query-parameters.
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public abstract int countQuery(FkQuery query) throws InvalidDataException;

    /**
     * Streams the given @{@link FkQuery} and return
     * a stream of sorted, filtered Ids that match all criteria defined in the given query-parameters.
     * The page-size settings of the query-parameters will be ignored here.
     *
     * @param query query
     * @return stream of sorted, filtered Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public abstract Stream<T> streamQuery(FkQuery query) throws InvalidDataException;

    /**
     * Fetches the DTOs for the given Ids
     * <p>
     * This function needs to execute a jooq-query (MULTISET is recommended here!),
     * which selects all data from the different tables that are needed for producing the DTO.
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    public abstract List<D> fetch(List<T> ids);


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