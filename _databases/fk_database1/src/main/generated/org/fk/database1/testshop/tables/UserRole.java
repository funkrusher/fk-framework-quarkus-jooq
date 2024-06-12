/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.fk.database1.testshop.Keys;
import org.fk.database1.testshop.Testshop;
import org.fk.database1.testshop.tables.Role.RolePath;
import org.fk.database1.testshop.tables.User.UserPath;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
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
public class UserRole extends TableImpl<UserRoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop.user_role</code>
     */
    public static final UserRole USER_ROLE = new UserRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRoleRecord> getRecordType() {
        return UserRoleRecord.class;
    }

    /**
     * The column <code>testshop.user_role.userId</code>.
     */
    public final TableField<UserRoleRecord, Integer> userId = createField(DSL.name("userId"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>testshop.user_role.roleId</code>.
     */
    public final TableField<UserRoleRecord, String> roleId = createField(DSL.name("roleId"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    private UserRole(Name alias, Table<UserRoleRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private UserRole(Name alias, Table<UserRoleRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>testshop.user_role</code> table reference
     */
    public UserRole(String alias) {
        this(DSL.name(alias), USER_ROLE);
    }

    /**
     * Create an aliased <code>testshop.user_role</code> table reference
     */
    public UserRole(Name alias) {
        this(alias, USER_ROLE);
    }

    /**
     * Create a <code>testshop.user_role</code> table reference
     */
    public UserRole() {
        this(DSL.name("user_role"), null);
    }

    public <O extends Record> UserRole(Table<O> path, ForeignKey<O, UserRoleRecord> childPath, InverseForeignKey<O, UserRoleRecord> parentPath) {
        super(path, childPath, parentPath, USER_ROLE);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class UserRolePath extends UserRole implements Path<UserRoleRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> UserRolePath(Table<O> path, ForeignKey<O, UserRoleRecord> childPath, InverseForeignKey<O, UserRoleRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private UserRolePath(Name alias, Table<UserRoleRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public UserRolePath as(String alias) {
            return new UserRolePath(DSL.name(alias), this);
        }

        @Override
        public UserRolePath as(Name alias) {
            return new UserRolePath(alias, this);
        }

        @Override
        public UserRolePath as(Table<?> alias) {
            return new UserRolePath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Testshop.TESTSHOP;
    }

    @Override
    public UniqueKey<UserRoleRecord> getPrimaryKey() {
        return Keys.KEY_USER_ROLE_PRIMARY;
    }

    @Override
    public List<ForeignKey<UserRoleRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK_USER_ROLE_USERID, Keys.FK_USER_ROLE_ROLEID);
    }

    private transient UserPath _user;

    /**
     * Get the implicit join path to the <code>testshop.user</code> table.
     */
    public UserPath user() {
        if (_user == null)
            _user = new UserPath(this, Keys.FK_USER_ROLE_USERID, null);

        return _user;
    }

    private transient RolePath _role;

    /**
     * Get the implicit join path to the <code>testshop.role</code> table.
     */
    public RolePath role() {
        if (_role == null)
            _role = new RolePath(this, Keys.FK_USER_ROLE_ROLEID, null);

        return _role;
    }

    @Override
    public UserRole as(String alias) {
        return new UserRole(DSL.name(alias), this);
    }

    @Override
    public UserRole as(Name alias) {
        return new UserRole(alias, this);
    }

    @Override
    public UserRole as(Table<?> alias) {
        return new UserRole(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserRole rename(String name) {
        return new UserRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserRole rename(Name name) {
        return new UserRole(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserRole rename(Table<?> name) {
        return new UserRole(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole where(Condition condition) {
        return new UserRole(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UserRole where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UserRole where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UserRole where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UserRole where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UserRole whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}
