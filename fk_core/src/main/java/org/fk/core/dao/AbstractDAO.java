package org.fk.core.dao;

import jakarta.validation.constraints.NotNull;
import org.fk.codegen.dto.AbstractDTO;
import org.fk.core.repository.AbstractRepository;
import org.fk.core.util.exception.InvalidDataException;
import org.fk.core.util.query.*;
import org.fk.core.util.request.RequestContext;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

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

    // -------------------------------------------------------------------------
    // Constructors and initialisation
    // -------------------------------------------------------------------------

    protected AbstractDAO(DSLContext dsl, Table<R> table) {
        this.dsl = dsl;
        this.table = table;
        this.request = (RequestContext) dsl.data("request");
    }

    // ------------------------------------------------------------------------
    // Internal/Private Helper methods
    // ------------------------------------------------------------------------

    private List<R> transformToRecords(List<? extends Y> objects) {
        List<R> records = new ArrayList<>();
        boolean isDTO = false;
        if (!objects.isEmpty()) {
            if (objects.getFirst() instanceof AbstractDTO) {
                isDTO = true;
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
            } else {
                records.add(dsl().newRecord(table(), obj));
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

        // Get the fields of the table that are auto-increment
        Field<?>[] autoIncrementFields = table().fieldStream()
                .filter(f -> f.getDataType().identity())
                .toArray(Field[]::new);

        // Set the auto-increment fields to null using reflection
        for (Field<?> field : autoIncrementFields) {
            try {
                Field<T> recordField = (Field<T>) field;
                // TODO: when do we need to do this???
                //record.set(recordField, null);

                // note: setting changed=true, will also mark all fields as NULL that are NOT NULL in database,
                // and jooq would in that case not use the DEFAULT of the database, but will say that they
                // should not be null
                // record.changed(true);

            } catch (Exception e) {
                // Handle any exceptions that occur while setting the field to null
                // For example, if the field is not nullable, you may get a NullPointerException
                // You can log the exception or handle it in any other way that is appropriate for your application
                e.printStackTrace();
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
        for (Field<?> pkField : pk()) {
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

    protected <L> Field<L> autoIncrementField() {
        // Get the fields of the table that are auto-increment
        Field<L>[] autoIncrementFields = table().fieldStream()
                .filter(f -> f.getDataType().identity())
                .toArray(Field[]::new);
        if (autoIncrementFields.length > 0) {
            return autoIncrementFields[0];
        } else {
            return null;
        }
    }

    protected Field<?>[] pk() {
        UniqueKey<?> key = table.getPrimaryKey();
        return key == null ? null : key.getFieldsArray();
    }

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
     * Performs an <code>INSERT</code> statement for a given DTO.
     *
     * @param dto The DTO to be inserted
     * @return inserted DTO with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> A insertAndReturnDTO(A dto) {
        return insertAndReturnDTOs(singletonList(dto)).getFirst();
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be inserted
     * @return inserted DTOs with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> List<A> insertAndReturnDTOs(A... dtos) {
        return insertAndReturnDTOs(asList(dtos));
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be inserted
     * @return inserted DTOs with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> List<A> insertAndReturnDTOs(List<A> dtos) {
        List<R> records = transformToRecords(dtos);
        Result<R> result = insertAndReturn(records);
        int i = 0;
        for (R item : result) {
            Field<?> setField = records.get(i).field(autoIncrementField());
            records.get(i).setValue(setField, item.getValue(autoIncrementField()));
            i++;
        }
        int j = 0;
        for (R record : records) {
            A obj = dtos.get(j);
            record.into(obj);
            j++;
        }
        return dtos;
    }

    /**
     * Performs an <code>INSERT</code> statement for a given Record.
     *
     * @param record The record to be inserted
     * @return inserted record with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public R insertAndReturn(R record) {
        return insertAndReturn(singletonList(record)).getFirst();
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @return inserted records with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public List<R> insertAndReturn(R... records) {
        return insertAndReturn(asList(records));
    }

    /**
     * Performs a <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @return inserted records with database-generated ID
     * @throws DataAccessException if something went wrong executing the query
     */
    public Result<R> insertAndReturn(List<R> records) {
        List<R> inserts = prepareInserts(records);

        int k = 1;
        InsertSetMoreStep step = dsl().insertInto(inserts.getFirst().getTable())
                .set(inserts.getFirst());
        if (inserts.size() > 1) {
            step.set(inserts.get(k));
            k++;
        }
        Result<R> result = step
                .returning(autoIncrementField())
                .fetch();
        return result;
    }

    /**
     * Performs a <code>INSERT</code> statement for a given DTO.
     *
     * @param dto The DTO to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> int insertDTO(A dto) throws DataAccessException {
        return insertDTOs(singletonList(dto))[0];
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> int[] insertDTOs(A... dtos) throws DataAccessException {
        return insertDTOs(dtos);
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> int[] insertDTOs(List<A> dtos) throws DataAccessException {
        List<R> records = transformToRecords(dtos);
        return insert(records);
    }

    /**
     * Performs a <code>INSERT</code> statement for a given record.
     *
     * @param record The record to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public int insert(R record) throws DataAccessException {
        return insert(singletonList(record))[0];
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public int[] insert(R... records) throws DataAccessException {
        return insert(records);
    }

    /**
     * Performs a batch <code>INSERT</code> statement for a given set of records.
     *
     * @param records The records to be inserted
     * @throws DataAccessException if something went wrong executing the query
     */
    public int[] insert(List<R> records) throws DataAccessException {
        List<R> inserts = prepareInserts(records);
        if (inserts.size() == 1) {
            return new int[]{inserts.getFirst().insert()};
        } else if (!inserts.isEmpty()) {
            // Execute a batch INSERT
            return dsl().batchInsert(inserts).execute();
        }
        return new int[]{0};
    }

    /**
     * Performs an <code>UPDATE</code> statement for a given DTO.
     *
     * @param dto The DTO to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void updateDTO(Y dto) throws DataAccessException {
        updateDTOs(singletonList(dto));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void updateDTOs(Y... dtos) throws DataAccessException {
        updateDTOs(asList(dtos));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void updateDTOs(List<? extends Y> dtos) throws DataAccessException {
        List<R> records = transformToRecords(dtos);
        update(records);
    }

    /**
     * Performs an <code>UPDATE</code> statement for a given record.
     *
     * @param record The record to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(R record) throws DataAccessException {
        update(singletonList(record));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of records.
     *
     * @param records The records to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(R... records) throws DataAccessException {
        update(asList(records));
    }

    /**
     * Performs a batch <code>UPDATE</code> statement for a given set of records.
     *
     * @param records The records to be updated
     * @throws DataAccessException if something went wrong executing the query
     */
    public void update(List<R> records) throws DataAccessException {
        List<R> updates = prepareUpdates(records);
        if (updates.size() > 1) {
            // Execute a batch UPDATE
            List<UpdateConditionStep<R>> conditions = new ArrayList<>();
            for (R record : updates) {
                conditions.add(dsl().update(table())
                        .set(record)
                        .where(equal(pk(), getId(record))));
            }
            dsl().batch(conditions).execute();
        } else if (updates.size() == 1) {
            // Execute a regular UPDATE
            dsl().update(table()).set(updates.getFirst()).where(equal(pk(), getId(updates.getFirst()))).execute();
        }
    }

    /**
     * Performs a <code>DELETE</code> statement for a DTO
     *
     * @param dto The DTO to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> void deleteDTO(A dto) throws DataAccessException {
        deleteDTOs(singletonList(dto));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> void deleteDTOs(A... dtos) throws DataAccessException {
        deleteDTOs(asList(dtos));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of DTOs.
     *
     * @param dtos The DTOs to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public <A extends Y> void deleteDTOs(List<A> dtos) throws DataAccessException {
        List<R> records = transformToRecords(dtos);
        delete(records);
    }

    /**
     * Performs a <code>DELETE</code> statement for a record
     *
     * @param record The record to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(R record) throws DataAccessException {
        delete(singletonList(record));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of records.
     *
     * @param records The records to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(R... records) throws DataAccessException {
        delete(asList(records));
    }

    /**
     * Performs a <code>DELETE</code> statement for a given set of records.
     *
     * @param records The records to be deleted
     * @throws DataAccessException if something went wrong executing the query
     */
    public void delete(List<R> records) throws DataAccessException {
        if (records.size() > 1) {
            // Execute a batch DELETE
            dsl().batchDelete(records).execute();
        } else if (records.size() == 1) {
            // Execute a regular DELETE
            records.getFirst().delete();
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
        Field<?>[] pk = pk();
        if (pk != null)
            dsl().delete(table()).where(equal(pk, ids)).execute();
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
        Field<?>[] pk = pk();

        if (pk != null)
            return dsl()
                    .selectCount()
                    .from(table())
                    .where(equal(pk, id))
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
    public long count() throws DataAccessException {
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
    public List<R> findAll() throws DataAccessException {
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
        Field<?>[] pk = pk();

        if (pk != null)
            return dsl().selectFrom(table())
                    .where(equal(pk, id))
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

    /**
     * Find records by a given field and a range of values.
     *
     * @param field          The field to compare values against
     * @param lowerInclusive The range's lower bound (inclusive), or unbounded
     *                       if <code>null</code>.
     * @param upperInclusive The range's upper bound (inclusive), or unbounded
     *                       if <code>null</code>.
     * @return A list of records fulfilling
     * <code>field BETWEEN lowerInclusive AND upperInclusive</code>
     * @throws DataAccessException if something went wrong executing the query
     */
    public <Z> List<R> fetchRange(Field<Z> field, Z lowerInclusive, Z upperInclusive) throws DataAccessException {
        return dsl()
                .selectFrom(table())
                .where(
                        lowerInclusive == null
                                ? upperInclusive == null
                                ? DSL.noCondition()
                                : field.le(upperInclusive)
                                : upperInclusive == null
                                ? field.ge(lowerInclusive)
                                : field.between(lowerInclusive, upperInclusive))
                .fetch();
    }

    /**
     * Find records by a given field and a set of values.
     *
     * @param field  The field to compare values against
     * @param values The accepted values
     * @return A list of records fulfilling <code>field IN (values)</code>
     * @throws DataAccessException if something went wrong executing the query
     */
    public <Z> List<R> fetch(Field<Z> field, Z... values) throws DataAccessException {
        return fetch(field, Arrays.asList(values));
    }

    /**
     * Find records by a given field and a set of values.
     *
     * @param field  The field to compare values against
     * @param values The accepted values
     * @return A list of records fulfilling <code>field IN (values)</code>
     * @throws DataAccessException if something went wrong executing the query
     */
    public <Z> List<R> fetch(Field<Z> field, Collection<? extends Z> values) throws DataAccessException {
        return dsl()
                .selectFrom(table())
                .where(field.in(values))
                .fetch();
    }

    /**
     * Find a unique record by a given field and a value.
     *
     * @param field The field to compare value against
     * @param value The accepted value
     * @return A record fulfilling <code>field = value</code>, or
     * <code>null</code>
     * @throws DataAccessException This exception is thrown
     *                             <ul>
     *                             <li>if something went wrong executing the query</li>
     *                             <li>if the query returned more than one value</li>
     *                             </ul>
     */
    public <Z> R fetchOne(Field<Z> field, Z value) throws DataAccessException {
        return dsl()
                .selectFrom(table())
                .where(field.equal(value))
                .fetchOne();
    }

    /**
     * Find a unique record by a given field and a value.
     *
     * @param field The field to compare value against
     * @param value The accepted value
     * @return A record fulfilling <code>field = value</code>
     * @throws DataAccessException This exception is thrown
     *                             <ul>
     *                             <li>if something went wrong executing the query</li>
     *                             <li>if the query returned more than one value</li>
     *                             </ul>
     */
    public <Z> Optional<R> fetchOptional(Field<Z> field, Z value) throws DataAccessException {
        return Optional.ofNullable(fetchOne(field, value));
    }
}
