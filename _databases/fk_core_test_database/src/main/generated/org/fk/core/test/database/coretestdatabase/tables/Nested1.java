/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.fk.core.test.database.coretestdatabase.Coretestdatabase;
import org.fk.core.test.database.coretestdatabase.Keys;
import org.fk.core.test.database.coretestdatabase.tables.Basic1.Basic1Path;
import org.fk.core.test.database.coretestdatabase.tables.Basic2.Basic2Path;
import org.fk.core.test.database.coretestdatabase.tables.records.Nested1Record;
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
public class Nested1 extends TableImpl<Nested1Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>coreTestDatabase.Nested1</code>
     */
    public static final Nested1 NESTED1 = new Nested1();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Nested1Record> getRecordType() {
        return Nested1Record.class;
    }

    /**
     * The column <code>coreTestDatabase.Nested1.autoIncId</code>.
     */
    public final TableField<Nested1Record, Integer> AUTOINCID = createField(DSL.name("autoIncId"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.uuidId</code>.
     */
    public final TableField<Nested1Record, UUID> UUIDID = createField(DSL.name("uuidId"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.string1</code>.
     */
    public final TableField<Nested1Record, String> STRING1 = createField(DSL.name("string1"), SQLDataType.VARCHAR(50).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.string2</code>.
     */
    public final TableField<Nested1Record, String> STRING2 = createField(DSL.name("string2"), SQLDataType.VARCHAR(50).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.integer1</code>.
     */
    public final TableField<Nested1Record, Integer> INTEGER1 = createField(DSL.name("integer1"), SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.long1</code>.
     */
    public final TableField<Nested1Record, Long> LONG1 = createField(DSL.name("long1"), SQLDataType.BIGINT.defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.decimal1</code>.
     */
    public final TableField<Nested1Record, BigDecimal> DECIMAL1 = createField(DSL.name("decimal1"), SQLDataType.DECIMAL(10, 2).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.DECIMAL)), this, "");

    /**
     * The column <code>coreTestDatabase.Nested1.dateTime1</code>.
     */
    public final TableField<Nested1Record, LocalDateTime> DATETIME1 = createField(DSL.name("dateTime1"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.LOCALDATETIME)), this, "");

    private Nested1(Name alias, Table<Nested1Record> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Nested1(Name alias, Table<Nested1Record> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>coreTestDatabase.Nested1</code> table reference
     */
    public Nested1(String alias) {
        this(DSL.name(alias), NESTED1);
    }

    /**
     * Create an aliased <code>coreTestDatabase.Nested1</code> table reference
     */
    public Nested1(Name alias) {
        this(alias, NESTED1);
    }

    /**
     * Create a <code>coreTestDatabase.Nested1</code> table reference
     */
    public Nested1() {
        this(DSL.name("Nested1"), null);
    }

    public <O extends Record> Nested1(Table<O> path, ForeignKey<O, Nested1Record> childPath, InverseForeignKey<O, Nested1Record> parentPath) {
        super(path, childPath, parentPath, NESTED1);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class Nested1Path extends Nested1 implements Path<Nested1Record> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> Nested1Path(Table<O> path, ForeignKey<O, Nested1Record> childPath, InverseForeignKey<O, Nested1Record> parentPath) {
            super(path, childPath, parentPath);
        }
        private Nested1Path(Name alias, Table<Nested1Record> aliased) {
            super(alias, aliased);
        }

        @Override
        public Nested1Path as(String alias) {
            return new Nested1Path(DSL.name(alias), this);
        }

        @Override
        public Nested1Path as(Name alias) {
            return new Nested1Path(alias, this);
        }

        @Override
        public Nested1Path as(Table<?> alias) {
            return new Nested1Path(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Coretestdatabase.CORETESTDATABASE;
    }

    @Override
    public UniqueKey<Nested1Record> getPrimaryKey() {
        return Keys.KEY_NESTED1_PRIMARY;
    }

    @Override
    public List<ForeignKey<Nested1Record, ?>> getReferences() {
        return Arrays.asList(Keys.FK_NESTED1_AUTOINCID, Keys.FK_NESTED1_UUIDID);
    }

    private transient Basic1Path _basic1;

    /**
     * Get the implicit join path to the <code>coreTestDatabase.Basic1</code>
     * table.
     */
    public Basic1Path basic1() {
        if (_basic1 == null)
            _basic1 = new Basic1Path(this, Keys.FK_NESTED1_AUTOINCID, null);

        return _basic1;
    }

    private transient Basic2Path _basic2;

    /**
     * Get the implicit join path to the <code>coreTestDatabase.Basic2</code>
     * table.
     */
    public Basic2Path basic2() {
        if (_basic2 == null)
            _basic2 = new Basic2Path(this, Keys.FK_NESTED1_UUIDID, null);

        return _basic2;
    }

    @Override
    public Nested1 as(String alias) {
        return new Nested1(DSL.name(alias), this);
    }

    @Override
    public Nested1 as(Name alias) {
        return new Nested1(alias, this);
    }

    @Override
    public Nested1 as(Table<?> alias) {
        return new Nested1(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Nested1 rename(String name) {
        return new Nested1(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Nested1 rename(Name name) {
        return new Nested1(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Nested1 rename(Table<?> name) {
        return new Nested1(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 where(Condition condition) {
        return new Nested1(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Nested1 where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Nested1 where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Nested1 where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Nested1 where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Nested1 whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
