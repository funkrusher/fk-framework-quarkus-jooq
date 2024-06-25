/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables;


import java.util.Collection;

import org.fk.database1.testshop.Keys;
import org.fk.database1.testshop.Testshop;
import org.fk.database1.testshop.tables.UserRole.UserRolePath;
import org.fk.database1.testshop.tables.records.RoleRecord;
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
public class Role extends TableImpl<RoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.role</code>
     */
    public static final Role ROLE = new Role();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RoleRecord> getRecordType() {
        return RoleRecord.class;
    }

    /**
     * The column <code>testshop.role.roleId</code>.
     */
    public final TableField<RoleRecord, String> ROLEID = createField(DSL.name("roleId"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private Role(Name alias, Table<RoleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Role(Name alias, Table<RoleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.role</code> table reference
     */
    public Role(String alias) {
        this(DSL.name(alias), ROLE);
    }

    /**
     * Create an aliased <code>testshop.role</code> table reference
     */
    public Role(Name alias) {
        this(alias, ROLE);
    }

    /**
     * Create a <code>testshop.role</code> table reference
     */
    public Role() {
        this(DSL.name("role"), null);
    }

    public <O extends Record> Role(Table<O> path, ForeignKey<O, RoleRecord> childPath, InverseForeignKey<O, RoleRecord> parentPath) {
        super(path, childPath, parentPath, ROLE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class RolePath extends Role implements Path<RoleRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> RolePath(Table<O> path, ForeignKey<O, RoleRecord> childPath, InverseForeignKey<O, RoleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private RolePath(Name alias, Table<RoleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public RolePath as(String alias) {
            return new RolePath(DSL.name(alias), this);
        }

        @Override
        public RolePath as(Name alias) {
            return new RolePath(alias, this);
        }

        @Override
        public RolePath as(Table<?> alias) {
            return new RolePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public UniqueKey<RoleRecord> getPrimaryKey() {
        return Keys.KEY_ROLE_PRIMARY;
    }

    private transient UserRolePath _user_role;

    /**
     * Get the implicit to-many join path to the <code>testshop.user_role</code>
     * table
     */
    public UserRolePath user_role() {
        if (_user_role == null)
            _user_role = new UserRolePath(this, null, Keys.FK_USER_ROLE_ROLEID.getInverseKey());

        return _user_role;
    }

    @Override
    public Role as(String alias) {
        return new Role(DSL.name(alias), this);
    }

    @Override
    public Role as(Name alias) {
        return new Role(alias, this);
    }

    @Override
    public Role as(Table<?> alias) {
        return new Role(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(String name) {
        return new Role(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(Name name) {
        return new Role(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(Table<?> name) {
        return new Role(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role where(Condition condition) {
        return new Role(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Role where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Role where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Role where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Role where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Role whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
