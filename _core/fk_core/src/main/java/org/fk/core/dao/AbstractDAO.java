package org.fk.core.dao;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import org.fk.core.dto.AbstractDTO;
import org.fk.core.jooq.RequestContext;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.fk.core.jooq.RequestContext.DSL_DATA_KEY;

/**
 * A common base-class for DAOs
 * <p>
 * This type is implemented by DAO classes to provide a common context-scoped API
 * for common actions
 * </p>
 * @param <R> The generic record type.
 * @param <T> The generic primary key type. This is a regular
 *            <code>&lt;T&gt;</code> type for single-column keys, or a
 *            {@link Record} subtype for composite keys.
 */
public abstract class AbstractDAO<R extends UpdatableRecord<R>,Y, T> {

    private final DSLContext dsl;
    private final RequestContext request;
    private final Table<R> table;
    private final UniqueKey<?> pk;
    private final Field<?>[] pkAutoIncFields;
    private final Field<UUID>[] pkUuidFields;

    // -------------------------------------------------------------------------
    // Constructors and initialization
    // -------------------------------------------------------------------------

    protected AbstractDAO(DSLContext dsl, Table<R> table) {
        this.dsl = dsl;
        this.table = table;
        this.request = (RequestContext) dsl.data(DSL_DATA_KEY);

        // primary-key, auto-inc, uuid.
        this.pk = table.getPrimaryKey();
        if (this.pk == null) {
            throw new RuntimeException("table has no primary-key! this is currently unsupported");
        }
        this.pkAutoIncFields = Arrays.stream(this.pk.getFieldsArray())
                .filter(f -> f.getDataType().identity()).toArray(Field[]::new);
        this.pkUuidFields = (Field<UUID>[]) Arrays.stream(this.pk.getFieldsArray())
                .filter(f -> f.getDataType().isUUID()).toArray(Field[]::new);
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    private List<R> transformToRecords(List<? extends Y> objects) {
        // TODO: this function must be refactored! instanceof-checks are not good.
        // we only support Records or AbstractDTO here.
        List<R> records = new ArrayList<>();
        boolean isDTO = false;
        boolean isRecord = false;
        if (!objects.isEmpty()) {
            if (objects.getFirst() instanceof AbstractDTO) {
                isDTO = true;
            } else if (objects.getFirst() instanceof UpdatableRecord<?>) {
                isRecord = true;
            }
        }
        for (Y obj : objects) {
            if (isDTO) {
                // create new record for this pojo/table that has no change-mark for all its fields
                // then set the change-marks
                AbstractDTO pojo = (AbstractDTO) obj;
                R record = dsl().newRecord(table(), obj);
                record.changed(false);

                for (Field<?> field : record.fields()) {
                    boolean isModified = pojo.getModifiedFields().containsKey(field.getName());
                    if (isModified) {
                        record.changed(field.getName(), true);
                    }
                }
                records.add(record);
            } else if (isRecord){
                records.add((R) obj);
            } else {
                throw new RuntimeException("unsupported type!");
            }
        }
        return records;
    }

    private List<R> prepareInserts(List<R> records) {
        List<R> results = new ArrayList<>();
        for (R record : records) {
            results.add(prepareInsert(record));
        }
        return results;
    }

    private R prepareInsert(R record) {
        // Get the table definition from the record
        Table<?> table = record.getTable();

        // Set the auto-increment fields to null using reflection
        for (Field<?> field : pkAutoIncFields) {
            try {
                // touch the auto-increment-fields as changed=true,
                // because when all fields are changed=false, then jooq will not process the insert,
                // so that way we can make sure that the insert is processed for such rare cases.
                Field<T> recordField = (Field<T>) field;
                record.changed(recordField, true);
            } catch (Exception e) {
                // Handle any exceptions that occur while setting the field to null
                // For example, if the field is not nullable, you may get a NullPointerException
                // You can log the exception or handle it in any other way that is appropriate for your application
                e.printStackTrace();
            }
        }
        for (Field<UUID> field : pkUuidFields) {
            // generate ulid id.
            if (dsl().dialect() == SQLDialect.MARIADB) {
                // mariadb uses RFC4122 UUID, we must use it here, or we get an error in insert.
                Ulid ulid = UlidCreator.getMonotonicUlid();
                record.set(field, ulid.toRfc4122().toUuid());
            } else {
                // should be changed for additional db-types.
                throw new RuntimeException("unsupported dialect for ULID-generation, please add your dialect here.");
            }
        }
        return record;
    }

    private List<R> prepareUpdates(List<R> records) {
        List<R> results = new ArrayList<>();
        for (R record : records) {
            results.add(prepareUpdate(record));
        }
        return results;
    }

    private R prepareUpdate(R record) {
        // Set the auto-increment fields to null using reflection
        for (Field<?> pkField : pk.getFieldsArray()) {
            try {
                // primary key values are never allowed to be changed for an update!
                // the database will give an error, if we try to change them in an update clause
                // they are only allowed to be used in where-clause of the update.
                Field<T> recordField = (Field<T>) pkField;
                record.changed(recordField, false);

            } catch (Exception e) {
                // Handle any exceptions that occur while setting the field to null
                // For example, if the field is not nullable, you may get a NullPointerException
                // You can log the exception or handle it in any other way that is appropriate for your application
                e.printStackTrace();
            }
        }
        return record;
    }


    // ------------------------------------------------------------------------
    // Template methods for subclasses
    // ------------------------------------------------------------------------

    /**
     * Extract the ID value of the given record
     *
     * @param record record of the table
     * @return value of the id field(s) of the given record.
     */
    protected abstract T getId(R record);


    // ------------------------------------------------------------------------
    // Helper methods for subclasses, for extracting/resolving common meta.
    // ------------------------------------------------------------------------

    protected T compositeKeyRecord(Object... values) {
        UniqueKey<R> key = table().getPrimaryKey();
        if (key == null)
            return null;

        TableField<R, Object>[] fields = (TableField<R, Object>[]) key.getFieldsArray();
        Record result = dsl().newRecord(fields);

        for (int i = 0; i < values.length; i++)
            result.set(fields[i], fields[i].getDataType().convert(values[i]));

        return (T) result;
    }

    protected Condition equal(Field<?>[] pk, T id) {
        if (pk.length == 1)
            return ((Field<Object>) pk[0]).equal(pk[0].getDataType().convert(id));

            // [#2573] Composite key T types are of type Record[N]
        else
            return DSL.row(pk).equal((Record) id);
    }

    protected Condition equal(Field<?>[] pk, Collection<T> ids) {
        if (pk.length == 1)
            if (ids.size() == 1)
                return equal(pk, ids.iterator().next());
            else
                return ((Field<Object>) pk[0]).in(pk[0].getDataType().convert(ids));

            // [#2573] Composite key T types are of type Record[N]
        else
            return DSL.row(pk).in(ids.toArray(new Record[]{}));
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
     * Expose the request this <code>DAO</code> is operating.
     *
     * @return the <code>DAO</code>'s underlying <code>request</code>
     */
    public RequestContext request() {
        return this.request;
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
     * Performs a <code>INSERT</code> statement for a given record.
     *
     * @param record The record to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public int insert(Y record) throws DataAccessException {
        return insert(singletonList(record))[0];
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public int[] insert(Y... records) throws DataAccessException {
        return insert(asList(records));
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A> int[] insert(List<Y> records) throws DataAccessException {
        List<R> records0 = transformToRecords(records);
        List<R> inserts = prepareInserts(records0);

        if (pkAutoIncFields.length > 0) {
            // we want to resolve the autoincrement from the database into the model
            // we need to use a way that has lower performance...
            Field<A> firstAutoIncField = (Field<A>) pkAutoIncFields[0];
            int k = 1;
            InsertSetMoreStep step = dsl().insertInto(inserts.getFirst().getTable())
                    .set(inserts.getFirst());
            if (inserts.size() > 1) {
                step.set(inserts.get(k));
                k++;
            }
            Result<R> result = step
                    .returning(firstAutoIncField)
                    .fetch();

            int i = 0;
            for (R item : result) {
                A value = item.getValue(firstAutoIncField);
                records0.get(i).setValue(firstAutoIncField, value);
                i++;
            }
            int j = 0;
            for (R record : records0) {
                Y obj = records.get(j);
                record.into(obj);
                j++;
            }
            return new int[]{result.size()};
        } else {
            // we have no autoincrement-field
            // we can insert with bulk-inserts to gain performance.
            int j = 0;
            for (R record : inserts) {
                Y obj = records.get(j);
                record.into(obj);
                j++;
            }

            if (inserts.size() == 1) {
                // Execute a regular INSERT (logging looks nicer that way)
                return new int[] {dsl().executeInsert(inserts.getFirst())};
            } else if (!inserts.isEmpty()) {
                // Execute a batch INSERT
                return dsl().batchInsert(inserts).execute();
            }
            return new int[]{0};
        }
    }

    /**
     * Performs an <code>UPDATE</code> statement for a given record.
     *
     * @param record The record to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(Y record) throws DataAccessException {
        update(singletonList(record));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of records.
     *
     * @param records The records to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(Y... records) throws DataAccessException {
        update(asList(records));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of records.
     *
     * @param records The records to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(List<Y> records) throws DataAccessException {
        List<R> records0 = transformToRecords(records);
        List<R> updates = prepareUpdates(records0);
        if (updates.size() > 1) {
            // Execute a batch UPDATE
            List<UpdateConditionStep<R>> conditions = new ArrayList<>();
            for (R record : updates) {
                conditions.add(dsl().update(table())
                        .set(record)
                        .where(equal(pk.getFieldsArray(), getId(record))));
            }
            dsl().batch(conditions).execute();
        } else if (updates.size() == 1) {
            // Execute a regular UPDATE (logging looks nicer that way)
            dsl().update(table()).set(updates.getFirst()).where(equal(pk.getFieldsArray(), getId(updates.getFirst()))).execute();
        }
    }

    /**
     * Performs a <code>DELETE</code> statement for a record
     *
     * @param record The record to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(Y record) throws DataAccessException {
        delete(singletonList(record));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of records.
     *
     * @param records The records to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(Y... records) throws DataAccessException {
        delete(asList(records));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of records.
     *
     * @param records The records to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(List<Y> records) throws DataAccessException {
        List<R> records0 = transformToRecords(records);
        if (records0.size() > 1) {
            // Execute a batch DELETE
            dsl().batchDelete(records0).execute();
        } else if (records0.size() == 1) {
            // Execute a regular DELETE (logging looks nicer that way)
            dsl().executeDelete(records0.getFirst());
        }
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs.
     *
     * @param id The ID to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void deleteById(T id) throws DataAccessException {
        deleteById(singletonList(id));
    }


    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs.
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void deleteById(T... ids) throws DataAccessException {
        deleteById(asList(ids));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of IDs.
     *
     * @param ids The IDs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void deleteById(Collection<T> ids) throws DataAccessException {
        if (pk != null)
            dsl().delete(table()).where(equal(pk.getFieldsArray(), ids)).execute();
    }

    /**
     * Checks if a given record exists.
     *
     * @param object The record whose existence is checked
     * @return Whether the record already exists
     * @throws DataAccessException if something went wrong executing the query
     */
    public boolean exists(R object) throws DataAccessException {
        return existsById(getId(object));
    }

    /**
     * Checks if a given ID exists.
     *
     * @param id The ID whose existence is checked
     * @return Whether the ID already exists
     * @throws DataAccessException if something went wrong executing the query
     */
    public boolean existsById(T id) throws DataAccessException {
        if (pk != null)
            return dsl()
                    .selectCount()
                    .from(table())
                    .where(equal(pk.getFieldsArray(), id))
                    .fetchOne(0, Integer.class) > 0;
        else
            return false;
    }

    /**
     * Count all records of the underlying table.
     *
     * @return The number of records of the underlying table
     * @throws DataAccessException if something went wrong executing the query
     */
    public long countAll() throws DataAccessException {
        return dsl()
                .selectCount()
                .from(table())
                .fetchOne(0, Long.class);
    }

    /**
     * Find all records of the underlying table.
     *
     * @return All records of the underlying table
     * @throws DataAccessException if something went wrong executing the query
     */
    public List<R> fetchAll() throws DataAccessException {
        return dsl()
                .selectFrom(table())
                .fetch();
    }

    /**
     * Find a record of the underlying table by ID.
     *
     * @param id The ID of a record in the underlying table
     * @return A record of the underlying table given its ID, or
     * <code>null</code> if no record was found.
     * @throws DataAccessException if something went wrong executing the query
     */
    public R findById(T id) throws DataAccessException {
        if (pk != null)
            return dsl().selectFrom(table())
                    .where(equal(pk.getFieldsArray(), id))
                    .fetchOne();
        return null;
    }

    /**
     * Find a record of the underlying table by ID.
     *
     * @param id The ID of a record in the underlying table
     * @return A record of the underlying table given its ID.
     * @throws DataAccessException if something went wrong executing the query
     */
    public Optional<R> findOptionalById(T id) throws DataAccessException {
        return Optional.ofNullable(findById(id));
    }
}
