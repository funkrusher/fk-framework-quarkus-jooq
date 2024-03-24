/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.interfaces;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface ILang extends Serializable {

    /**
     * Setter for <code>testshop.lang.langId</code>.
     */
    public void setLangId(Integer value);

    /**
     * Getter for <code>testshop.lang.langId</code>.
     */
    public Integer getLangId();

    /**
     * Setter for <code>testshop.lang.code</code>.
     */
    public void setCode(String value);

    /**
     * Getter for <code>testshop.lang.code</code>.
     */
    @NotNull
    @Size(max = 2)
    public String getCode();

    /**
     * Setter for <code>testshop.lang.description</code>.
     */
    public void setDescription(String value);

    /**
     * Getter for <code>testshop.lang.description</code>.
     */
    @Size(max = 50)
    public String getDescription();

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
