/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.fk.database1.testshop.Keys;
import org.fk.database1.testshop.Testshop;
import org.fk.database1.testshop.tables.QrtzTriggers.QrtzTriggersPath;
import org.fk.database1.testshop.tables.records.QrtzCronTriggersRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
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
public class QrtzCronTriggers extends TableImpl<QrtzCronTriggersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.QRTZ_CRON_TRIGGERS</code>
     */
    public static final QrtzCronTriggers QRTZ_CRON_TRIGGERS = new QrtzCronTriggers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QrtzCronTriggersRecord> getRecordType() {
        return QrtzCronTriggersRecord.class;
    }

    /**
     * The column <code>testshop.QRTZ_CRON_TRIGGERS.SCHED_NAME</code>.
     */
    public final TableField<QrtzCronTriggersRecord, String> SCHED_NAME = createField(DSL.name("SCHED_NAME"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_NAME</code>.
     */
    public final TableField<QrtzCronTriggersRecord, String> TRIGGER_NAME = createField(DSL.name("TRIGGER_NAME"), SQLDataType.VARCHAR(190).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_CRON_TRIGGERS.TRIGGER_GROUP</code>.
     */
    public final TableField<QrtzCronTriggersRecord, String> TRIGGER_GROUP = createField(DSL.name("TRIGGER_GROUP"), SQLDataType.VARCHAR(190).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_CRON_TRIGGERS.CRON_EXPRESSION</code>.
     */
    public final TableField<QrtzCronTriggersRecord, String> CRON_EXPRESSION = createField(DSL.name("CRON_EXPRESSION"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_CRON_TRIGGERS.TIME_ZONE_ID</code>.
     */
    public final TableField<QrtzCronTriggersRecord, String> TIME_ZONE_ID = createField(DSL.name("TIME_ZONE_ID"), SQLDataType.VARCHAR(80).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    private QrtzCronTriggers(Name alias, Table<QrtzCronTriggersRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private QrtzCronTriggers(Name alias, Table<QrtzCronTriggersRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_CRON_TRIGGERS</code> table
     * reference
     */
    public QrtzCronTriggers(String alias) {
        this(DSL.name(alias), QRTZ_CRON_TRIGGERS);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_CRON_TRIGGERS</code> table
     * reference
     */
    public QrtzCronTriggers(Name alias) {
        this(alias, QRTZ_CRON_TRIGGERS);
    }

    /**
     * Create a <code>testshop.QRTZ_CRON_TRIGGERS</code> table reference
     */
    public QrtzCronTriggers() {
        this(DSL.name("QRTZ_CRON_TRIGGERS"), null);
    }

    public <O extends Record> QrtzCronTriggers(Table<O> path, ForeignKey<O, QrtzCronTriggersRecord> childPath, InverseForeignKey<O, QrtzCronTriggersRecord> parentPath) {
        super(path, childPath, parentPath, QRTZ_CRON_TRIGGERS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class QrtzCronTriggersPath extends QrtzCronTriggers implements Path<QrtzCronTriggersRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> QrtzCronTriggersPath(Table<O> path, ForeignKey<O, QrtzCronTriggersRecord> childPath, InverseForeignKey<O, QrtzCronTriggersRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private QrtzCronTriggersPath(Name alias, Table<QrtzCronTriggersRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public QrtzCronTriggersPath as(String alias) {
            return new QrtzCronTriggersPath(DSL.name(alias), this);
        }

        @Override
        public QrtzCronTriggersPath as(Name alias) {
            return new QrtzCronTriggersPath(alias, this);
        }

        @Override
        public QrtzCronTriggersPath as(Table<?> alias) {
            return new QrtzCronTriggersPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public UniqueKey<QrtzCronTriggersRecord> getPrimaryKey() {
        return Keys.KEY_QRTZ_CRON_TRIGGERS_PRIMARY;
    }

    @Override
    public List<ForeignKey<QrtzCronTriggersRecord, ?>> getReferences() {
        return Arrays.asList(Keys.QRTZ_CRON_TRIGGERS_IBFK_1);
    }

    private transient QrtzTriggersPath __1;

    /**
     * Get the implicit join path to the <code>testshop.QRTZ_TRIGGERS</code>
     * table.
     */
    public QrtzTriggersPath _1() {
        if (__1 == null)
            __1 = new QrtzTriggersPath(this, Keys.QRTZ_CRON_TRIGGERS_IBFK_1, null);

        return __1;
    }

    @Override
    public QrtzCronTriggers as(String alias) {
        return new QrtzCronTriggers(DSL.name(alias), this);
    }

    @Override
    public QrtzCronTriggers as(Name alias) {
        return new QrtzCronTriggers(alias, this);
    }

    @Override
    public QrtzCronTriggers as(Table<?> alias) {
        return new QrtzCronTriggers(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzCronTriggers rename(String name) {
        return new QrtzCronTriggers(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzCronTriggers rename(Name name) {
        return new QrtzCronTriggers(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzCronTriggers rename(Table<?> name) {
        return new QrtzCronTriggers(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers where(Condition condition) {
        return new QrtzCronTriggers(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzCronTriggers where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzCronTriggers where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzCronTriggers where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzCronTriggers where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzCronTriggers whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
