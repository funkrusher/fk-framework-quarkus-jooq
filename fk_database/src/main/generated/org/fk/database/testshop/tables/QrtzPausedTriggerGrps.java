/*
 * This file is generated by jOOQ.
 */
package org.fk.database.testshop.tables;


import java.util.Collection;

import org.fk.database.testshop.Keys;
import org.fk.database.testshop.Testshop;
import org.fk.database.testshop.tables.records.QrtzPausedTriggerGrpsRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
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
public class QrtzPausedTriggerGrps extends TableImpl<QrtzPausedTriggerGrpsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS</code>
     */
    public static final QrtzPausedTriggerGrps QRTZ_PAUSED_TRIGGER_GRPS = new QrtzPausedTriggerGrps();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QrtzPausedTriggerGrpsRecord> getRecordType() {
        return QrtzPausedTriggerGrpsRecord.class;
    }

    /**
     * The column <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.SCHED_NAME</code>.
     */
    public final TableField<QrtzPausedTriggerGrpsRecord, String> SCHED_NAME = createField(DSL.name("SCHED_NAME"), SQLDataType.VARCHAR(120).nullable(false), this, "");

    /**
     * The column <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS.TRIGGER_GROUP</code>.
     */
    public final TableField<QrtzPausedTriggerGrpsRecord, String> TRIGGER_GROUP = createField(DSL.name("TRIGGER_GROUP"), SQLDataType.VARCHAR(190).nullable(false), this, "");

    private QrtzPausedTriggerGrps(Name alias, Table<QrtzPausedTriggerGrpsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private QrtzPausedTriggerGrps(Name alias, Table<QrtzPausedTriggerGrpsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS</code> table
     * reference
     */
    public QrtzPausedTriggerGrps(String alias) {
        this(DSL.name(alias), QRTZ_PAUSED_TRIGGER_GRPS);
    }

    /**
     * Create an aliased <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS</code> table
     * reference
     */
    public QrtzPausedTriggerGrps(Name alias) {
        this(alias, QRTZ_PAUSED_TRIGGER_GRPS);
    }

    /**
     * Create a <code>testshop.QRTZ_PAUSED_TRIGGER_GRPS</code> table reference
     */
    public QrtzPausedTriggerGrps() {
        this(DSL.name("QRTZ_PAUSED_TRIGGER_GRPS"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public UniqueKey<QrtzPausedTriggerGrpsRecord> getPrimaryKey() {
        return Keys.KEY_QRTZ_PAUSED_TRIGGER_GRPS_PRIMARY;
    }

    @Override
    public QrtzPausedTriggerGrps as(String alias) {
        return new QrtzPausedTriggerGrps(DSL.name(alias), this);
    }

    @Override
    public QrtzPausedTriggerGrps as(Name alias) {
        return new QrtzPausedTriggerGrps(alias, this);
    }

    @Override
    public QrtzPausedTriggerGrps as(Table<?> alias) {
        return new QrtzPausedTriggerGrps(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzPausedTriggerGrps rename(String name) {
        return new QrtzPausedTriggerGrps(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzPausedTriggerGrps rename(Name name) {
        return new QrtzPausedTriggerGrps(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public QrtzPausedTriggerGrps rename(Table<?> name) {
        return new QrtzPausedTriggerGrps(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps where(Condition condition) {
        return new QrtzPausedTriggerGrps(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzPausedTriggerGrps where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzPausedTriggerGrps where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzPausedTriggerGrps where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public QrtzPausedTriggerGrps where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public QrtzPausedTriggerGrps whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}