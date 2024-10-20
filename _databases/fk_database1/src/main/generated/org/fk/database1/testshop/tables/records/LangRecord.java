/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.Lang;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Lang contains available languages of the app
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LangRecord extends UpdatableRecordImpl<LangRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.lang.langId</code>. langId
     */
    public LangRecord setLangid(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>testshop.lang.langId</code>. langId
     */
    public Integer getLangid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    public LangRecord setCode(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @NotNull
    @Size(max = 2)
    public String getCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    public LangRecord setDescription(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Size(max = 50)
    public String getDescription() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LangRecord
     */
    public LangRecord() {
        super(Lang.LANG);
    }

    /**
     * Create a detached, initialised LangRecord
     */
    public LangRecord(Integer langid, String code, String description) {
        super(Lang.LANG);

        setLangid(langid);
        setCode(code);
        setDescription(description);
        resetChangedOnNotNull();
    }
}
