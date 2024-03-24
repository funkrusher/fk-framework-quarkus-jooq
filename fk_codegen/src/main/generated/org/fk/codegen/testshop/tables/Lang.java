/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables;


import java.util.Collection;

import org.fk.codegen.testshop.Keys;
import org.fk.codegen.testshop.Testshop;
import org.fk.codegen.testshop.tables.Product.ProductPath;
import org.fk.codegen.testshop.tables.ProductLang.ProductLangPath;
import org.fk.codegen.testshop.tables.records.LangRecord;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
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
public class Lang extends TableImpl<LangRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.lang</code>
     */
    public static final Lang LANG = new Lang();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LangRecord> getRecordType() {
        return LangRecord.class;
    }

    /**
     * The column <code>testshop.lang.langId</code>.
     */
    public final TableField<LangRecord, Integer> LANGID = createField(DSL.name("langId"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>testshop.lang.code</code>.
     */
    public final TableField<LangRecord, String> CODE = createField(DSL.name("code"), SQLDataType.CHAR(2).nullable(false), this, "");

    /**
     * The column <code>testshop.lang.description</code>.
     */
    public final TableField<LangRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.VARCHAR(50).defaultValue(DSL.field(DSL.raw("NULL"), SQLDataType.VARCHAR)), this, "");

    private Lang(Name alias, Table<LangRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Lang(Name alias, Table<LangRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.lang</code> table reference
     */
    public Lang(String alias) {
        this(DSL.name(alias), LANG);
    }

    /**
     * Create an aliased <code>testshop.lang</code> table reference
     */
    public Lang(Name alias) {
        this(alias, LANG);
    }

    /**
     * Create a <code>testshop.lang</code> table reference
     */
    public Lang() {
        this(DSL.name("lang"), null);
    }

    public <O extends Record> Lang(Table<O> path, ForeignKey<O, LangRecord> childPath, InverseForeignKey<O, LangRecord> parentPath) {
        super(path, childPath, parentPath, LANG);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class LangPath extends Lang implements Path<LangRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> LangPath(Table<O> path, ForeignKey<O, LangRecord> childPath, InverseForeignKey<O, LangRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private LangPath(Name alias, Table<LangRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public LangPath as(String alias) {
            return new LangPath(DSL.name(alias), this);
        }

        @Override
        public LangPath as(Name alias) {
            return new LangPath(alias, this);
        }

        @Override
        public LangPath as(Table<?> alias) {
            return new LangPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public Identity<LangRecord, Integer> getIdentity() {
        return (Identity<LangRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<LangRecord> getPrimaryKey() {
        return Keys.KEY_LANG_PRIMARY;
    }

    private transient ProductLangPath _fk_product_lang_langId;

    /**
     * Get the implicit to-many join path to the
     * <code>testshop.product_lang</code> table
     */
    public ProductLangPath fk_product_lang_langId() {
        if (_fk_product_lang_langId == null)
            _fk_product_lang_langId = new ProductLangPath(this, null, Keys.FK_PRODUCT_LANG_LANGID.getInverseKey());

        return _fk_product_lang_langId;
    }

    /**
     * Get the implicit many-to-many join path to the
     * <code>testshop.product</code> table
     */
    public ProductPath fk_product_lang_productId() {
        return fk_product_lang_langId().fk_product_lang_productId();
    }

    @Override
    public Lang as(String alias) {
        return new Lang(DSL.name(alias), this);
    }

    @Override
    public Lang as(Name alias) {
        return new Lang(alias, this);
    }

    @Override
    public Lang as(Table<?> alias) {
        return new Lang(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Lang rename(String name) {
        return new Lang(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Lang rename(Name name) {
        return new Lang(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Lang rename(Table<?> name) {
        return new Lang(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang where(Condition condition) {
        return new Lang(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Lang where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Lang where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Lang where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Lang where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Lang whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
