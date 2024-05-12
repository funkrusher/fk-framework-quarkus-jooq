package org.fk.core.dao;

import org.fk.core.dto.DTO;
import org.fk.core.exception.MappingException;
import org.fk.core.request.RequestContext;
import org.fk.core.ulid.UlidGenerator;
import org.jetbrains.annotations.Nullable;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.util.*;

import static java.util.Arrays.asList;
import static org.fk.core.dao.AbstractDAO.InsertType.*;
import static org.fk.core.request.RequestContext.DSL_DATA_KEY;

/**
 * A common base-class for DAOs
 * <p>
 * This type is implemented by DAO classes to provide a common context-scoped API for common actions
 * </p>
 * @param <R> The generic rec type. This must be a jOOQ generated Record.
 * @param <Y> The generic interface that R needs to implement. This must be a jOOQ generated Interface
 * @param <T> The generic primary key type. Either a regular type for single-column keys,
 *          or a {@link Record} subtype for composite keys.
 */
public abstract class AbstractDAO<R extends UpdatableRecord<R>,Y, T> {
    private final DSLContext dsl;
    private final Table<R> table;
    private final RequestContext request;
    private final UniqueKey<R> pk;
    private final @Nullable Field<Object> autoIncGeneratingField;
    private final @Nullable Field<UUID> uuidGeneratingField;
    private final @Nullable Field<Integer> clientIdField;

    // -------------------------------------------------------------------------
    // Constructors and initialization
    // -------------------------------------------------------------------------

    protected AbstractDAO(final DSLContext dsl, final Table<R> table) {
        this.dsl = dsl;
        this.table = table;
        this.request = (RequestContext) dsl.data(DSL_DATA_KEY);
        if (this.request == null) {
            throw new MappingException("request is missing!");
        }
        if (table.getPrimaryKey() == null) {
            throw new MappingException("table must have a primary-key");
        }
        this.pk = table.getPrimaryKey();

        // search the 'generating' field in the primary-key
        // most tables have such a 'generating' field. Currently supported: either an auto-inc or uuid field.
        // some tables (like N:M tables, don't have such a field, which is also supported)
        // we will use this, to automatically generate and return those ids to the caller.
        this.autoIncGeneratingField = AbstractDAO.findAutoIncGeneratingField(pk);
        this.uuidGeneratingField = AbstractDAO.findUuidGeneratingField(pk);

        // if table has a field clientId, we must match it always with request-context clientId.
        final Field<Integer> field = DSL.field(DSL.name("clientId"), Integer.class);
        this.clientIdField = this.table.field(field);
        if (this.clientIdField != null && this.request.getClientId() == null) {
            // fail-fast if we expect a clientId in any case!
            throw new MappingException("DAO table contains field clientId but request is missing!");
        }
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    /**
     * Checks if the given primary-key field is not also a foreign-key
     * Only Primary-Keys that are not foreign-keys can be 'generating' primary-keys,
     * that we can create via autoinc or uuid generator.
     *
     * @param primaryKeyField primaryKeyField
     * @return true/false
     */
    private static boolean isGeneratingPk(TableField<?, ?> primaryKeyField) {
        return Objects.requireNonNull(primaryKeyField.getTable())
                .getReferences().stream()
                .noneMatch(fk -> fk.getFields().getFirst().getName().equals(primaryKeyField.getName()));
    }

    /**
     * Finds the Non-FK AutoInc Primary-Key in the given table (we only expect one or none)
     *
     * @param pk pk
     * @return autoinc pk field
     * @param <Q> table-type
     */
    private static <Q extends UpdatableRecord<Q>> @Nullable Field<Object> findAutoIncGeneratingField(final UniqueKey<Q> pk) {
        //noinspection unchecked
        return (Field<Object>) pk.getFields().stream()
                .filter(field -> isGeneratingPk(field) && field.getDataType().identity())
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds the Non-FK UUID Primary-Key in the given table (we only expect one or none)
     *
     * @param pk pk
     * @return uuid pk field
     * @param <Q> table-type
     */
    private static <Q extends UpdatableRecord<Q>> @Nullable Field<UUID> findUuidGeneratingField(final UniqueKey<Q> pk) {
        //noinspection unchecked
        return (Field<UUID>) pk.getFields().stream()
                .filter(field -> isGeneratingPk(field) && field.getDataType().isUUID())
                .findFirst()
                .orElse(null);
    }

    /**
     * The main-purpose of this function is, to convert the DTO-classes to the Record-Classes, because
     * only Record-Classes can directly be used within the jooq-statements, and we must map the modified-fields
     * of the DTO-classes to the Record-classes.
     *
     * @param items items (Records of DTOs)
     * @return recs
     */
    private List<R> prepareRecords(final List<? extends Y> items) {
        if (items.isEmpty()) {
            return Collections.emptyList();
        } else if (items.getFirst() instanceof UpdatableRecord<?>) {
            //noinspection unchecked
            return (List<R>) items;
        } else if (items.getFirst() instanceof DTO) {
            final List<R> recs = new ArrayList<>();
            for (final Y item : items) {
                // transform DTO to Record.
                final DTO dto = (DTO) item;
                final R rec= dsl().newRecord(table(), dto);
                rec.changed(false);
                for (Field<?> field : rec.fields()) {
                    // we need to transfer the DTO-changed markers to the Record-changed markers.
                    if (dto.getBookKeeper().touched().containsKey(field.getName())) {
                        rec.changed(field.getName(), true);
                    }
                }
                recs.add(rec);
            }
            return recs;
        } else {
            // should be unreachable
            throw new MappingException("unexpected implementation of DAO-typed interface!");
        }
    }

    /**
     * Enforce the expected clientId into the given record.
     * @param rec rec
     */
    private void enforceExpectedClientId(R rec) {
        if (this.clientIdField != null && request != null) {
            rec.set(this.clientIdField, request.getClientId());
        }
    }

    /**
     * Enforces the expected change-status for the primary-key fields in the given record.
     * @param rec rec
     * @param changed change-status
     */
    private void enforcePrimaryKeysChangeStatus(R rec, boolean changed) {
        for (final Field<?> field : pk.getFieldsArray()) {
            rec.changed(field, changed);
        }
    }

    /**
     * Enforces that the given record has a generating id, and if not generated id and set it.
     * @param rec rec
     */
    private void enforceGeneratingIdExists(R rec) {
        if (uuidGeneratingField != null && rec.get(uuidGeneratingField) == null) {
            // uuid field in pk is empty, we need to generate it now.
            if (dsl().dialect() == SQLDialect.MARIADB) {
                // mariadb uses RFC4122 UUID, we must use it here, or we get an error in insert.
                rec.set(uuidGeneratingField, UlidGenerator.createMariadbUuid());
            } else {
                // should be changed for additional db-types.
                throw new MappingException("unexpected dialect for ulid generator!");
            }
        }
    }


    /**
     * The given Records will be prepared for an Insert-Statement.
     * - enforces the correct clientId into the rec.
     * - touches primary-key fields
     * - populates the generating primary-key uuid-field, if it does not already have a value given by the caller.
     *
     * @param recs recs
     * @return recs prepared for insert
     */
    private List<R> prepareInserts(final List<R> recs) {
        for (final R rec: recs) {
            this.enforceExpectedClientId(rec);
            this.enforceGeneratingIdExists(rec);

            // we need to 'touch' all fields of the primary-key,
            // so jooq recognizes them as always relevant for the insert.
            this.enforcePrimaryKeysChangeStatus(rec, true);
        }
        return recs;
    }

    /**
     * The given Records will be prepared for an Update-Statement.
     * - enforces the correct clientId into the rec.
     * - sets primary-key fields to 'unchanged', because in update pk-fields must never change.
     *
     * @param recs recs
     * @return recs prepared for update
     */
    private List<R> prepareUpdates(final List<R> recs) {
        for (final R rec: recs) {
            this.enforceExpectedClientId(rec);

            // primary key values are never allowed to be changed for an update!
            // the database will give an error, if we try to change them in an update clause
            this.enforcePrimaryKeysChangeStatus(rec, false);
        }
        return recs;
    }

    /**
     * The Record-Condition will be used for Update and Delete Statements,
     * to enforce that they work only on following WHERE-Criteria:
     * - enforces the correct clientId into the WHERE-Criteria.
     * - enforces the correct primary-key criteria into the WHERE-Criteria.
     *
     * @param rec rec
     * @return condition that must be used for all common Update and Delete Statements on this rec.
     */
    private Condition getRecordCondition(final R rec) {
        Condition condition = getPrimaryKeyCondition(getId(rec));
        if (this.clientIdField != null && request != null) {
            condition = condition.and(this.clientIdField.eq(request.getClientId()));
        }
        return condition;
    }

    /**
     * Resolves the Primary-Key Condition for the given pk-value
     * The Primary-Key may be a single field, but also can consist of multiple fields.
     *
     * @param value primary-key value
     * @return Condition for the given id
     */
    private Condition getPrimaryKeyCondition(final T value) {
        // we can safely cast '?' to Object, as we need Object here for calling equal as '?' is not viable for this.
        //noinspection unchecked
        final TableField<R, Object>[] fields = (TableField<R, Object>[]) this.pk.getFieldsArray();
        if (fields.length == 1)
            return (fields[0]).equal(fields[0].getDataType().convert(value));
        else
            return DSL.row(fields).equal((Record) value);
    }

    /**
     * Resolves the Primary-Key Condition for the given pk-values
     * The Primary-Key may be a single field, but also can consist of multiple fields.
     * The result will be an SQL-IN Clause, if the given pk-values consist of more than 1 value.
     *
     * @param values list of primary-key values
     * @return Condition for the given values
     */
    private Condition getPrimaryKeyCondition(final Collection<T> values) {
        // we can safely cast '?' to Object, as we need Object here for calling equal as '?' is not viable for this.
        //noinspection unchecked
        final TableField<R, Object>[] fields = (TableField<R, Object>[]) this.pk.getFieldsArray();
        if (fields.length == 1) {
            if (values.size() == 1)
                return getPrimaryKeyCondition(values.iterator().next());
            else
                return fields[0].in(fields[0].getDataType().convert(values));
        } else {
            // because fields.length > 1, we can be sure at this point that the T-value is a
            // collection of "Record" Tuple instances, therefore we can safely cast it to a Record-Array.
            //noinspection unchecked
            final Collection<Record> recs = (Collection<Record>) values;
            return DSL.row(fields).in(recs.toArray(new Record[]{}));
        }
    }

    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    /**
     * Extract the ID value of the given rec
     * You need to provide this function in your DTO, and define the correct mapping.
     *
     * @param rec rec of the table
     * @return value of the id field(s) of the given rec.
     */
    protected abstract T getId(final R rec);


    // ------------------------------------------------------------------------
    // Helper methods for subclasses, for extracting/resolving common meta.
    // ------------------------------------------------------------------------

    /**
     * Helper-function needed to be used together with {@link #getId(UpdatableRecord)} in your DTO implementation,
     * if your getId implementation needs to provide the rec for a primary-key that consists of more than one field.
     *
     * @param values values that must be mappable to the T-class.
     * @return instance of T (combined primary-key)
     */
    public T compositeKeyRecord(final Object... values) {
        // we can safely cast '?' to Object, as we need Object here for calling equal as '?' is not viable for this.
        //noinspection unchecked
        final TableField<R, Object>[] fields = (TableField<R, Object>[]) this.pk.getFieldsArray();
        final Record result = dsl().newRecord(fields);

        for (int i = 0; i < values.length; i++)
            result.set(fields[i], fields[i].getDataType().convert(values[i]));

        // TODO: this would crash at runtime, when the user provides values that do not fit to the T-class.
        return (T) result;
    }


    // -------------------------------------------------------------------------
    // DAO API
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
     * Expose the table this <code>DAO</code> is operating.
     *
     * @return the <code>DAO</code>'s underlying <code>table</code>
     */
    public Table<R> table() {
        return this.table;
    }

    /**
     * InsertType
     */
    public enum InsertType {
        INSERT,
        INSERT_ON_DUPLICATE_UPDATE,
        INSERT_ON_DUPLICATE_IGNORE
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of items.
     *
     * @param items The items to be inserted
     * @throws DataAccessException if something went wrong executing the query
     *
     * @return insert-count of successful inserts.
     */
    @SafeVarargs
    public final int insert(final Y... items) throws DataAccessException {
        return insert(asList(items));
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of items.
     *
     * @param items The items to be inserted
     * @throws DataAccessException if something went wrong executing the query
     *
     * @return insert-count of successful inserts.
     */
    public int insert(final List<Y> items) throws DataAccessException {
        return this.insert(items, INSERT);
    }

    /**
     * Performs a <code>UPSERT</code> statement for a given set of items.
     * If an item exists by its id, it will be updated, otherwise it will be inserted.
     * (with help of: SQL ON DUPLICATE KEY UPDATE)
     *
     * @param items The items to be upserted
     * @throws DataAccessException if something went wrong executing the query
     *
     * @return upsert-count of successful upserts.
     */
    @SafeVarargs
    public final int upsert(final Y... items) throws DataAccessException {
        return upsert(asList(items));
    }

    /**
     * Performs a <code>UPSERT</code> statement for a given set of items.
     * If an item exists by its id, it will be updated, otherwise it will be inserted.
     * (with help of: SQL ON DUPLICATE KEY UPDATE)
     *
     * @param items The items to be upserted
     * @throws DataAccessException if something went wrong executing the query
     *
     * @return upsert-count of successful upserts.
     */
    public int upsert(final List<Y> items) throws DataAccessException {
        return this.insert(items, INSERT_ON_DUPLICATE_UPDATE);
    }

    private InsertReturningStep<R> setInsertQueryOptions(InsertSetMoreStep<R> insert, InsertType insertType) {
        // prepare our insert-query with different options for different use-cases.
        if (insertType == INSERT) {
            return insert;
        } else if (insertType == INSERT_ON_DUPLICATE_UPDATE) {
            return insert
                    .onDuplicateKeyUpdate()
                    .setAllToExcluded();
        } else if (insertType == INSERT_ON_DUPLICATE_IGNORE) {
            return insert.onDuplicateKeyIgnore();
        } else {
            throw new MappingException("unsupported insert-type!");
        }
    }

    private int insert(final List<Y> items, InsertType insertType) throws DataAccessException {
        final List<R> inserts = prepareInserts(prepareRecords(items));
        if (inserts.isEmpty()) {
            return 0;
        }
        int insertCount = 0;

        if (autoIncGeneratingField != null) {
            // low-performance-route, we need to retrieve autoinc ids from the db.
            List<Object> autoIncResults = new ArrayList<>();
            for (R insert : inserts) {
                // execute each INSERT one-by-one to retrieve its autoinc id.
                InsertReturningStep<R> insertQuery = setInsertQueryOptions(dsl()
                        .insertInto(inserts.getFirst().getTable())
                        .set(insert), insertType);
                Result<R> rec = insertQuery.returning(autoIncGeneratingField).fetch();
                autoIncResults.add(rec.getFirst().getValue(autoIncGeneratingField));
            }
            insertCount = autoIncResults.size();

            // database has created autoinc-ids, we have retrieved them from the db,
            // now we must reflect them back into the inserts, so the caller has them available.
            // we need to use generic type A here which may be a concrete subclass of Number (we already made sure)
            for (int j = 0; j < items.size(); j++) {
                inserts.get(j).setValue(autoIncGeneratingField, autoIncResults.get(j));
            }
        } else {
            // inserts that have uuid primary-key, or no primary-key.
            // high-performance-route, we do not need to resolve anything back from the db
            // - use batch-statements (db will execute them in a big bulk, and not one-by-one)
            // - use multi-value insert-statements (one INSERT statement contains a chunk of items instead of only one)

            int chunkSize = 10; // Define the size of each multi-value-insert chunk
            List<Query> multiValueInserts = new ArrayList<>();

            // Loop over the original inserts and slice them into smaller multi-value-insert chunks.
            for (int i = 0; i < inserts.size(); i += chunkSize) {
                int end = Math.min(inserts.size(), i + chunkSize);
                List<R> chunk = inserts.subList(i, end);
                Query insertQuery = setInsertQueryOptions(dsl()
                        .insertInto(inserts.getFirst().getTable())
                        .set(chunk), insertType);

                multiValueInserts.add(insertQuery);
            }
            if (multiValueInserts.size() > 1) {
                // multi-value batch insert (huge performance boost)
                final int[] batchResult = dsl().batch(multiValueInserts).execute();
                for (int oneResult : batchResult) {
                    if (oneResult == 1) {
                        insertCount = insertCount + 1;
                    }
                }
            } else {
                // single insert
                insertCount = multiValueInserts.getFirst().execute();
            }
        }

        // inserts have been changed (got autinc-value, uuid-value, ...)
        // we need to reflect changes back into the given items,
        // so the caller has them available.
        for (int j = 0; j < items.size(); j++) {
            inserts.get(j).into(items.get(j));
        }
        return insertCount;
    }



    /**
     * Performs a <code>UPDATE</code> statement for a given set of items.
     *
     * @param items The items to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    @SafeVarargs
    public final void update(final Y... items) throws DataAccessException {
        update(asList(items));
    }

    /**
     * Performs a <code>UPDATE</code> statement for a given set of items.
     *
     * @param items The items to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(final List<Y> items) throws DataAccessException {
        final List<R> updates = prepareUpdates(prepareRecords(items));
        if (updates.isEmpty()) {
            return;
        }
        if (updates.size() > 1) {
            // batch update (performance gain)
            final List<UpdateConditionStep<R>> conditions = new ArrayList<>();
            for (R rec : updates) {
                conditions.add(dsl().update(table())
                        .set(rec)
                        .where(getRecordCondition(rec)));
            }
            dsl().batch(conditions).execute();
        } else {
            // single update
            final R rec = updates.getFirst();
            dsl().update(table())
                    .set(rec)
                    .where(getRecordCondition(rec))
                    .execute();
        }
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of items.
     *
     * @param items The items to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    @SafeVarargs
    public final void delete(final Y... items) throws DataAccessException {
        delete(asList(items));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of items.
     *
     * @param items The items to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(final List<Y> items) throws DataAccessException {
        final List<R> deletes = prepareRecords(items);
        if (deletes.isEmpty()) {
            return;
        }
        if (deletes.size() > 1) {
            // batch delete (performance gain)
            final List<DeleteConditionStep<R>> conditions = new ArrayList<>();
            for (R rec : deletes) {
                final Condition deleteCondition = getRecordCondition(rec).and(getPrimaryKeyCondition(getId(rec)));
                conditions.add(dsl().delete(table()).where(deleteCondition));
            }
            dsl().batch(conditions).execute();
        } else {
            // single delete
            final R rec = deletes.getFirst();
            final Condition deleteCondition = getRecordCondition(rec).and(getPrimaryKeyCondition(getId(rec)));
            dsl().delete(table()).where(deleteCondition).execute();
        }
    }

   /**
     * Performs a <code>DELETE</code> statement for a given set of IDs.
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    @SafeVarargs
    public final void deleteById(final T... ids) throws DataAccessException {
        deleteById(asList(ids));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs.
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void deleteById(final Collection<T> ids) throws DataAccessException {
        if (pk != null)
            dsl()
                    .delete(table())
                    .where(getPrimaryKeyCondition(ids))
                    .execute();
    }

    /**
     * Checks if a given ID exists.
     *
     * @param id The ID whose existence is checked
     * @return Whether the ID already exists
     * @throws DataAccessException if something went wrong executing the query
     */
    public boolean existsById(final T id) throws DataAccessException {
        final SelectConditionStep<R> selectStep = dsl.selectFrom(table()).where(getPrimaryKeyCondition(id));
        return dsl().fetchCount(selectStep) > 0;
    }

    /**
     * Count all recs of the underlying table.
     *
     * @return The number of recs of the underlying table
     * @throws DataAccessException if something went wrong executing the query
     */
    public int countAll() throws DataAccessException {
        return dsl().fetchCount(dsl.selectFrom(table()));
    }

    /**
     * Fetch a rec of the underlying table by ID.
     *
     * @param id The ID of a rec in the underlying table
     * @return A rec of the underlying table given its ID, or
     * <code>null</code> if no rec was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    public @Nullable R fetch(final T id) throws DataAccessException {
        return dsl().selectFrom(table())
                .where(getPrimaryKeyCondition(id))
                .fetchOne();
    }
}
