/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop2.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop2.tables.ProductLang;
import org.jooq.Record2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProductLangRecord extends UpdatableRecordImpl<ProductLangRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop2.product_lang.productId</code>.
     */
    public ProductLangRecord setProductid(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.productId</code>.
     */
    @NotNull
    public Long getProductid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>testshop2.product_lang.langId</code>.
     */
    public ProductLangRecord setLangid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.langId</code>.
     */
    @NotNull
    public Integer getLangid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>testshop2.product_lang.name</code>.
     */
    public ProductLangRecord setName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>testshop2.product_lang.description</code>.
     */
    public ProductLangRecord setDescription(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>testshop2.product_lang.description</code>.
     */
    @NotNull
    @Size(max = 65535)
    public String getDescription() {
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
    public ProductLangRecord(Long productid, Integer langid, String name, String description) {
        super(ProductLang.PRODUCT_LANG);

        setProductid(productid);
        setLangid(langid);
        setName(name);
        setDescription(description);
        resetChangedOnNotNull();
    }
}
