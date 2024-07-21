package org.fk.core.repository;

import org.fk.core.dto.DTO;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.query.jooq.QueryFunction;
import org.fk.core.query.jooq.QueryExecutor;
import org.fk.core.query.model.FkFilter;
import org.fk.core.query.model.FkQuery;
import org.fk.core.request.RequestContext;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;

import java.util.*;
import java.util.stream.Stream;

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
    private final Field<T> idField;
    private final RequestContext request;

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractRepository(DSLContext dsl, Field<T> idField) {
        this.dsl = dsl;
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
     * Create a @{@link QueryExecutor} from the given @{@link QueryFunction}
     * - You can define functions in your repository that have a @{@link FkQuery} as parameter,
     * and return a @{@link SelectFinalStep<Record1<D>>} as a result.
     * Such functions can be used as @{@link QueryFunction} via the "::" notation.
     *
     * @param query query
     * @return @{@link QueryExecutor}
     */
    public QueryExecutor<D, T> executor(QueryFunction<D> query) {
        return new QueryExecutor<>(idField, query);
    }

    /**
     * Execute Query on the given @{@link QueryFunction}, by applying the given @{@link FkQuery} to it.
     *
     * @param f f
     * @param query query
     * @return list of sorted, filtered, page-sized DTOs
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public List<D> query(final QueryFunction<D> f, final FkQuery query) throws InvalidDataException {
        return executor(f).query(query);
    }

    /**
     * Execute Count on the given @{@link QueryFunction}, by applying the given @{@link FkFilter}s to it.
     *
     * @param f f
     * @param filters filters
     * @return total count of items, matching the given filters
     * @throws InvalidDataException invalid data in the given filters
     */
    public int count(final QueryFunction<D> f, final List<FkFilter> filters) throws InvalidDataException {
        return executor(f).count(filters);
    }

    /**
     * Execute Stream on the given @{@link QueryFunction}, by applying the given @{@link FkQuery} to it.
     *
     * @return stream of sorted, filtered Ids
     * @throws InvalidDataException invalid data in the given queryParameters
     */
    public Stream<D> stream(final QueryFunction<D> f, final FkQuery query) throws InvalidDataException {
        return executor(f).stream(query);
    }

    /**
     * Execute Fetch on the given @{@link QueryFunction}, by selecting the given ids.
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    public final List<D> fetch(final QueryFunction<D> f, final List<T> ids) {
        return executor(f).fetch(ids);
    }

    /**
     * Execute Fetch on the given @{@link QueryFunction}, by selecting the given id.
     *
     * @param id id
     * @return dto for given id
     */
    public @Nullable D fetch(final QueryFunction<D> f, final T id) {
        return executor(f).fetch(id);
    }

    /**
     * Execute Fetch on the given @{@link QueryFunction}, by selecting the given ids.
     *
     * @param ids ids
     * @return dtos dtos for given ids
     */
    @SafeVarargs
    public final List<D> fetch(final QueryFunction<D> f, final T... ids) {
        return executor(f).fetch(ids);
    }
}