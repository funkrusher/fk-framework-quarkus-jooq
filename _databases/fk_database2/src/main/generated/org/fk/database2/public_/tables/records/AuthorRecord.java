/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.records;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.database2.public_.tables.Author;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class AuthorRecord extends UpdatableRecordImpl<AuthorRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.author.author_id</code>.
     */
    public AuthorRecord setAuthorId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.author.author_id</code>.
     */
    public Integer getAuthorId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.author.name</code>.
     */
    public AuthorRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.author.name</code>.
     */
    @NotNull
    @Size(max = 100)
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.author.nationality</code>.
     */
    public AuthorRecord setNationality(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.author.nationality</code>.
     */
    @Size(max = 50)
    public String getNationality() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.author.birth_date</code>.
     */
    public AuthorRecord setBirthDate(LocalDate value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.author.birth_date</code>.
     */
    public LocalDate getBirthDate() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.author.biography</code>.
     */
    public AuthorRecord setBiography(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.author.biography</code>.
     */
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
    public AuthorRecord(Integer authorId, String name, String nationality, LocalDate birthDate, String biography) {
        super(Author.AUTHOR);

        setAuthorId(authorId);
        setName(name);
        setNationality(nationality);
        setBirthDate(birthDate);
        setBiography(biography);
        resetChangedOnNotNull();
    }
}
