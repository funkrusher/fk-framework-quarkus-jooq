/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.fk.database1.testshop.Indexes;
import org.fk.database1.testshop.Keys;
import org.fk.database1.testshop.Testshop;
import org.fk.database1.testshop.tables.QrtzTriggers.QrtzTriggersPath;
import org.fk.database1.testshop.tables.records.QrtzJobDetailsRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class QrtzJobDetails extends TableImpl<QrtzJobDetailsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.QRTZ_JOB_DETAILS</code>
     */
    public static final QrtzJobDetails QRTZ_JOB_DETAILS = new QrtzJobDetails();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QrtzJobDetailsRecord> getRecordType() {
        return QrtzJobDetailsRecord.class;
    }

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.SCHED_NAME</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> SCHED_NAME = createField(DSL.name("SCHED_NAME"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.JOB_NAME</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> JOB_NAME = createField(DSL.name("JOB_NAME"), SQLDataType.VARCHAR(190).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.JOB_GROUP</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> JOB_GROUP = createField(DSL.name("JOB_GROUP"), SQLDataType.VARCHAR(190).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.DESCRIPTION</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> DESCRIPTION = createField(DSL.name("DESCRIPTION"), SQLDataType.VARCHAR(250).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.JOB_CLASS_NAME</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> JOB_CLASS_NAME = createField(DSL.name("JOB_CLASS_NAME"), SQLDataType.VARCHAR(250).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.IS_DURABLE</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> IS_DURABLE = createField(DSL.name("IS_DURABLE"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.IS_NONCONCURRENT</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> IS_NONCONCURRENT = createField(DSL.name("IS_NONCONCURRENT"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.IS_UPDATE_DATA</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> IS_UPDATE_DATA = createField(DSL.name("IS_UPDATE_DATA"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.REQUESTS_RECOVERY</code>.
     */
    public final TableField<QrtzJobDetailsRecord, String> REQUESTS_RECOVERY = createField(DSL.name("REQUESTS_RECOVERY"), SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_JOB_DETAILS.JOB_DATA</code>.
     */
    public final TableField<QrtzJobDetailsRecord, byte[]> JOB_DATA = createField(DSL.name("JOB_DATA"), SQLDataType.BLOB.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BLOB)), this, "");

    private QrtzJobDetails(Name alias, Table<QrtzJobDetailsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private QrtzJobDetails(Name alias, Table<QrtzJobDetailsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_JOB_DETAILS</code> table reference
     */
    public QrtzJobDetails(String alias) {
        this(DSL.name(alias), QRTZ_JOB_DETAILS);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_JOB_DETAILS</code> table reference
     */
    public QrtzJobDetails(Name alias) {
        this(alias, QRTZ_JOB_DETAILS);
    }

    /**
     * Create a <code>testshop.QRTZ_JOB_DETAILS</code> table reference
     */
    public QrtzJobDetails() {
        this(DSL.name("QRTZ_JOB_DETAILS"), null);
    }

    public <O extends Record> QrtzJobDetails(Table<O> path, ForeignKey<O, QrtzJobDetailsRecord> childPath, InverseForeignKey<O, QrtzJobDetailsRecord> parentPath) {
        super(path, childPath, parentPath, QRTZ_JOB_DETAILS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class QrtzJobDetailsPath extends QrtzJobDetails implements Path<QrtzJobDetailsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> QrtzJobDetailsPath(Table<O> path, ForeignKey<O, QrtzJobDetailsRecord> childPath, InverseForeignKey<O, QrtzJobDetailsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private QrtzJobDetailsPath(Name alias, Table<QrtzJobDetailsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public QrtzJobDetailsPath as(String alias) {
            return new QrtzJobDetailsPath(DSL.name(alias), this);
        }

        @Override
        public QrtzJobDetailsPath as(Name alias) {
            return new QrtzJobDetailsPath(alias, this);
        }

        @Override
        public QrtzJobDetailsPath as(Table<?> alias) {
            return new QrtzJobDetailsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.QRTZ_JOB_DETAILS_IDX_QRTZ_J_GRP, Indexes.QRTZ_JOB_DETAILS_IDX_QRTZ_J_REQ_RECOVERY);
    }

    @Override
    public UniqueKey<QrtzJobDetailsRecord> getPrimaryKey() {
        return Keys.KEY_QRTZ_JOB_DETAILS_PRIMARY;
    }

    private transient QrtzTriggersPath __1;

    /**
     * Get the implicit to-many join path to the
     * <code>testshop.QRTZ_TRIGGERS</code> table
     */
    public QrtzTriggersPath _1() {
        if (__1 == null)
            __1 = new QrtzTriggersPath(this, null, Keys.QRTZ_TRIGGERS_IBFK_1.getInverseKey());

        return __1;
    }

    @Override
    public QrtzJobDetails as(String alias) {
        return new QrtzJobDetails(DSL.name(alias), this);
    }

    @Override
    public QrtzJobDetails as(Name alias) {
        return new QrtzJobDetails(alias, this);
    }

    @Override
    public QrtzJobDetails as(Table<?> alias) {
        return new QrtzJobDetails(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzJobDetails rename(String name) {
        return new QrtzJobDetails(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzJobDetails rename(Name name) {
        return new QrtzJobDetails(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzJobDetails rename(Table<?> name) {
        return new QrtzJobDetails(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails where(Condition condition) {
        return new QrtzJobDetails(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzJobDetails where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzJobDetails where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzJobDetails where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzJobDetails where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzJobDetails whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
