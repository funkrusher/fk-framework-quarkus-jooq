/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop2.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProductLangRecord extends UpdatableRecordImpl<ProductLangRecord> implements IProductLang {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop2.product_lang.productId</code>.
     */
    @Override
    public ProductLangRecord ProductId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.productId</code>.
     */
    @NotNull
    @Override
    public Long ProductId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>testshop2.product_lang.langId</code>.
     */
    @Override
    public ProductLangRecord LangId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.langId</code>.
     */
    @NotNull
    @Override
    public Integer LangId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>testshop2.product_lang.name</code>.
     */
    @Override
    public ProductLangRecord Name(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.name</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String Name() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop2.product_lang.description</code>.
     */
    @Override
    public ProductLangRecord Description(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.description</code>.
     */
    @NotNull
    @Size(max = 65535)
    @Override
    public String Description() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Long, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IProductLang from) {
        ProductId(from.ProductId());
        LangId(from.LangId());
        Name(from.Name());
        Description(from.Description());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IProductLang> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductLangRecord
     */
    public ProductLangRecord() {
        super(ProductLang.PRODUCT_LANG);
    }

    /**
     * Create a detached, initialised ProductLangRecord
     */
    public ProductLangRecord(Long productId, Integer langId, String name, String description) {
        super(ProductLang.PRODUCT_LANG);

        ProductId(productId);
        LangId(langId);
        Name(name);
        Description(description);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ProductLangRecord
     */
    public ProductLangRecord(org.fk.database1.testshop2.tables.pojos.ProductLang value) {
        super(ProductLang.PRODUCT_LANG);

        if (value != null) {
            ProductId(value.ProductId());
            LangId(value.LangId());
            Name(value.Name());
            Description(value.Description());
            resetChangedOnNotNull();
        }
    }
}
