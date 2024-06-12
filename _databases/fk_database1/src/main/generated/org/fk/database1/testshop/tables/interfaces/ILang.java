/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.interfaces;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * Lang contains available languages of the app
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface ILang extends Serializable {

    /**
     * Setter for <code>testshop.lang.langId</code>. langId
     */
    public ILang LangId(Integer value);

    /**
     * Getter for <code>testshop.lang.langId</code>. langId
     */
    public Integer LangId();

    /**
     * Setter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    public ILang Code(String value);

    /**
     * Getter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @NotNull
    @Size(max = 2)
    public String Code();

    /**
     * Setter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    public ILang Description(String value);

    /**
     * Getter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Size(max = 50)
    public String Description();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface ILang
     */
    public void from(ILang from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface ILang
     */
    public <E extends ILang> E into(E into);
}
