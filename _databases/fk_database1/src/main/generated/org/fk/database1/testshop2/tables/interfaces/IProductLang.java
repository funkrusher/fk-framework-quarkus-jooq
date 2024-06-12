/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop2.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IProductLang extends Serializable {

    /**
     * Setter for <code>testshop2.product_lang.productId</code>.
     */
    public IProductLang ProductId(Long value);

    /**
     * Getter for <code>testshop2.product_lang.productId</code>.
     */
    @NotNull
    public Long ProductId();

    /**
     * Setter for <code>testshop2.product_lang.langId</code>.
     */
    public IProductLang LangId(Integer value);

    /**
     * Getter for <code>testshop2.product_lang.langId</code>.
     */
    @NotNull
    public Integer LangId();

    /**
     * Setter for <code>testshop2.product_lang.name</code>.
     */
    public IProductLang Name(String value);

    /**
     * Getter for <code>testshop2.product_lang.name</code>.
     */
    @NotNull
    @Size(max = 255)
    public String Name();

    /**
     * Setter for <code>testshop2.product_lang.description</code>.
     */
    public IProductLang Description(String value);

    /**
     * Getter for <code>testshop2.product_lang.description</code>.
     */
    @NotNull
    @Size(max = 65535)
    public String Description();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IProductLang
     */
    public void from(IProductLang from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IProductLang
     */
    public <E extends IProductLang> E into(E into);
}
