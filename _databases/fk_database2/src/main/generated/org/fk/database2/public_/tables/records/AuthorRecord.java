/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.database2.public_.tables.Author;
import org.fk.database2.public_.tables.interfaces.IAuthor;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class AuthorRecord extends UpdatableRecordImpl<AuthorRecord> implements IAuthor {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.author.author_id</code>.
     */
    @Override
    public AuthorRecord setAuthor_id(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.author.author_id</code>.
     */
    @Override
    public Integer getAuthor_id() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.author.name</code>.
     */
    @Override
    public AuthorRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.author.name</code>.
     */
    @NotNull
    @Size(max = 100)
    @Override
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.author.nationality</code>.
     */
    @Override
    public AuthorRecord setNationality(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.author.nationality</code>.
     */
    @Size(max = 50)
    @Override
    public String getNationality() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.author.birth_date</code>.
     */
    @Override
    public AuthorRecord setBirth_date(LocalDate value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.author.birth_date</code>.
     */
    @Override
    public LocalDate getBirth_date() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.author.biography</code>.
     */
    @Override
    public AuthorRecord setBiography(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.author.biography</code>.
     */
    @Override
    public String getBiography() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IAuthor from) {
        setAuthor_id(from.getAuthor_id());
        setName(from.getName());
        setNationality(from.getNationality());
        setBirth_date(from.getBirth_date());
        setBiography(from.getBiography());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IAuthor> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AuthorRecord
     */
    public AuthorRecord() {
        super(Author.AUTHOR);
    }

    /**
     * Create a detached, initialised AuthorRecord
     */
    public AuthorRecord(Integer author_id, String name, String nationality, LocalDate birth_date, String biography) {
        super(Author.AUTHOR);

        setAuthor_id(author_id);
        setName(name);
        setNationality(nationality);
        setBirth_date(birth_date);
        setBiography(biography);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised AuthorRecord
     */
    public AuthorRecord(org.fk.database2.public_.tables.pojos.Author value) {
        super(Author.AUTHOR);

        if (value != null) {
            setAuthor_id(value.getAuthor_id());
            setName(value.getName());
            setNationality(value.getNationality());
            setBirth_date(value.getBirth_date());
            setBiography(value.getBiography());
            resetChangedOnNotNull();
        }
    }
}
