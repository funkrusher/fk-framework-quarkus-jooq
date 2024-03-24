/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop;


import java.util.Arrays;
import java.util.List;

import org.fk.codegen.DefaultCatalog;
import org.fk.codegen.testshop.tables.Client;
import org.fk.codegen.testshop.tables.Databasechangelog;
import org.fk.codegen.testshop.tables.Databasechangeloglock;
import org.fk.codegen.testshop.tables.Lang;
import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.ProductLang;
import org.fk.codegen.testshop.tables.Role;
import org.fk.codegen.testshop.tables.User;
import org.fk.codegen.testshop.tables.UserRole;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Testshop extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop</code>
     */
    public static final Testshop TESTSHOP = new Testshop();

    /**
     * The table <code>testshop.client</code>.
     */
    public final Client CLIENT = Client.CLIENT;

    /**
     * The table <code>testshop.DATABASECHANGELOG</code>.
     */
    public final Databasechangelog DATABASECHANGELOG = Databasechangelog.DATABASECHANGELOG;

    /**
     * The table <code>testshop.DATABASECHANGELOGLOCK</code>.
     */
    public final Databasechangeloglock DATABASECHANGELOGLOCK = Databasechangeloglock.DATABASECHANGELOGLOCK;

    /**
     * The table <code>testshop.lang</code>.
     */
    public final Lang LANG = Lang.LANG;

    /**
     * The table <code>testshop.product</code>.
     */
    public final Product PRODUCT = Product.PRODUCT;

    /**
     * The table <code>testshop.product_lang</code>.
     */
    public final ProductLang PRODUCT_LANG = ProductLang.PRODUCT_LANG;

    /**
     * The table <code>testshop.role</code>.
     */
    public final Role ROLE = Role.ROLE;

    /**
     * The table <code>testshop.user</code>.
     */
    public final User USER = User.USER;

    /**
     * The table <code>testshop.user_role</code>.
     */
    public final UserRole USER_ROLE = UserRole.USER_ROLE;

    /**
     * No further instances allowed
     */
    private Testshop() {
        super("testshop", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Client.CLIENT,
            Databasechangelog.DATABASECHANGELOG,
            Databasechangeloglock.DATABASECHANGELOGLOCK,
            Lang.LANG,
            Product.PRODUCT,
            ProductLang.PRODUCT_LANG,
            Role.ROLE,
            User.USER,
            UserRole.USER_ROLE
        );
    }
}
