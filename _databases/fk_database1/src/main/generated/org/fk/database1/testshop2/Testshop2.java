/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop2;


import java.util.Arrays;
import java.util.List;

import org.fk.database1.DefaultCatalog;
import org.fk.database1.testshop2.tables.Product;
import org.fk.database1.testshop2.tables.ProductLang;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Testshop2 extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>testshop2</code>
     */
    public static final Testshop2 TESTSHOP2 = new Testshop2();

    /**
     * Client-specific Products
     */
    public final Product PRODUCT = Product.PRODUCT;

    /**
     * The table <code>testshop2.product_lang</code>.
     */
    public final ProductLang PRODUCT_LANG = ProductLang.PRODUCT_LANG;

    /**
     * No further instances allowed
     */
    private Testshop2() {
        super("testshop2", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Product.PRODUCT,
            ProductLang.PRODUCT_LANG
        );
    }
}
