/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables;


import java.util.Collection;
import java.util.UUID;

import org.fk.core.test.database.coretestdatabase.Coretestdatabase;
import org.fk.core.test.database.coretestdatabase.Keys;
import org.fk.core.test.database.coretestdatabase.tables.Nested1.Nested1Path;
import org.fk.core.test.database.coretestdatabase.tables.records.Basic2Record;
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
public class Basic2 extends TableImpl<Basic2Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>coreTestDatabase.Basic2</code>
     */
    public static final Basic2 BASIC2 = new Basic2();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Basic2Record> getRecordType() {
        return Basic2Record.class;
    }

    /**
     * The column <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    public final TableField<Basic2Record, UUID> UUIDID = createField(DSL.name("uuidId"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>coreTestDatabase.Basic2.string1</code>.
     */
    public final TableField<Basic2Record, String> STRING1 = createField(DSL.name("string1"), SQLDataType.VARCHAR(50).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>coreTestDatabase.Basic2.string2</code>.
     */
    public final TableField<Basic2Record, String> STRING2 = createField(DSL.name("string2"), SQLDataType.VARCHAR(50).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>coreTestDatabase.Basic2.clientId</code>.
     */
    public final TableField<Basic2Record, Integer> CLIENTID = createField(DSL.name("clientId"), SQLDataType.INTEGER.nullable(false), this, "");

    private Basic2(Name alias, Table<Basic2Record> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Basic2(Name alias, Table<Basic2Record> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>coreTestDatabase.Basic2</code> table reference
     */
    public Basic2(String alias) {
        this(DSL.name(alias), BASIC2);
    }

    /**
     * Create an aliased <code>coreTestDatabase.Basic2</code> table reference
     */
    public Basic2(Name alias) {
        this(alias, BASIC2);
    }

    /**
     * Create a <code>coreTestDatabase.Basic2</code> table reference
     */
    public Basic2() {
        this(DSL.name("Basic2"), null);
    }

    public <O extends Record> Basic2(Table<O> path, ForeignKey<O, Basic2Record> childPath, InverseForeignKey<O, Basic2Record> parentPath) {
        super(path, childPath, parentPath, BASIC2);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class Basic2Path extends Basic2 implements Path<Basic2Record> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> Basic2Path(Table<O> path, ForeignKey<O, Basic2Record> childPath, InverseForeignKey<O, Basic2Record> parentPath) {
            super(path, childPath, parentPath);
        }
        private Basic2Path(Name alias, Table<Basic2Record> aliased) {
            super(alias, aliased);
        }

        @Override
        public Basic2Path as(String alias) {
            return new Basic2Path(DSL.name(alias), this);
        }

        @Override
        public Basic2Path as(Name alias) {
            return new Basic2Path(alias, this);
        }

        @Override
        public Basic2Path as(Table<?> alias) {
            return new Basic2Path(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Coretestdatabase.CORETESTDATABASE;
    }

    @Override
    public UniqueKey<Basic2Record> getPrimaryKey() {
        return Keys.KEY_BASIC2_PRIMARY;
    }

    private transient Nested1Path _Nested1;

    /**
     * Get the implicit to-many join path to the
     * <code>coreTestDatabase.Nested1</code> table
     */
    public Nested1Path Nested1() {
        if (_Nested1 == null)
            _Nested1 = new Nested1Path(this, null, Keys.FK_NESTED1_UUIDID.getInverseKey());

        return _Nested1;
    }

    @Override
    public Basic2 as(String alias) {
        return new Basic2(DSL.name(alias), this);
    }

    @Override
    public Basic2 as(Name alias) {
        return new Basic2(alias, this);
    }

    @Override
    public Basic2 as(Table<?> alias) {
        return new Basic2(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Basic2 rename(String name) {
        return new Basic2(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Basic2 rename(Name name) {
        return new Basic2(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Basic2 rename(Table<?> name) {
        return new Basic2(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 where(Condition condition) {
        return new Basic2(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Basic2 where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Basic2 where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Basic2 where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Basic2 where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Basic2 whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
