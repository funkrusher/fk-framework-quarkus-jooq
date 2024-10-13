package org.fk.database1.testshop.tables.dtos;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.ILang;

/**
 * Lang contains available languages of the app
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LangDto<T extends LangDto> extends AbstractDTO implements ILang {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer langId;
    private String code;
    private String description;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public LangDto() {}

    public LangDto(ILang value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public Integer getLangId() {
        return this.langId;
    }

    /**
     * Setter for <code>testshop.lang.langId</code>. langId
     */
    @Override
    public T setLangId(Integer langId) {
        this.langId = langId;
        return (T) this;
    }

    /**
     * Getter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @NotNull
    @Size(max = 2)
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Setter for <code>testshop.lang.code</code>. ISO-639 language code
     */
    @Override
    public T setCode(String code) {
        this.code = code;
        return (T) this;
    }

    /**
     * Getter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Size(max = 50)
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>testshop.lang.description</code>. internal description
     * of language
     */
    @Override
    public T setDescription(String description) {
        this.description = description;
        return (T) this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(ILang from) {
        setLangId(from.getLangId());
        setCode(from.getCode());
        setDescription(from.getDescription());
    }

    @Override
    public <E extends ILang> E into(E into) {
        into.from(this);
        return into;
    }

}
