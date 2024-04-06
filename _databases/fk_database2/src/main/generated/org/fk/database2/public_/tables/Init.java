/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables;


import java.util.Collection;

import org.fk.database2.public_.Public;
import org.fk.database2.public_.tables.records.InitRecord;
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
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Init extends TableImpl<InitRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.init</code>
     */
    public static final Init INIT = new Init();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<InitRecord> getRecordType() {
        return InitRecord.class;
    }

    /**
     * The column <code>public.init.initialized</code>.
     */
    public final TableField<InitRecord, String> INITIALIZED = createField(DSL.name("initialized"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private Init(Name alias, Table<InitRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Init(Name alias, Table<InitRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.init</code> table reference
     */
    public Init(String alias) {
        this(DSL.name(alias), INIT);
    }

    /**
     * Create an aliased <code>public.init</code> table reference
     */
    public Init(Name alias) {
        this(alias, INIT);
    }

    /**
     * Create a <code>public.init</code> table reference
     */
    public Init() {
        this(DSL.name("init"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Init as(String alias) {
        return new Init(DSL.name(alias), this);
    }

    @Override
    public Init as(Name alias) {
        return new Init(alias, this);
    }

    @Override
    public Init as(Table<?> alias) {
        return new Init(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Init rename(String name) {
        return new Init(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Init rename(Name name) {
        return new Init(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Init rename(Table<?> name) {
        return new Init(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init where(Condition condition) {
        return new Init(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Init where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Init where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Init where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Init where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Init whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
