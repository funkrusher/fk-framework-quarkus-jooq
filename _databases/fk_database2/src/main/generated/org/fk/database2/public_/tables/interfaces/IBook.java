/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.interfaces;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IBook extends Serializable {

    /**
     * Setter for <code>public.book.book_id</code>.
     */
    public void setBook_id(Integer value);

    /**
     * Getter for <code>public.book.book_id</code>.
     */
    public Integer getBook_id();

    /**
     * Setter for <code>public.book.title</code>.
     */
    public void setTitle(String value);

    /**
     * Getter for <code>public.book.title</code>.
     */
    @NotNull
    @Size(max = 200)
    public String getTitle();

    /**
     * Setter for <code>public.book.author_id</code>.
     */
    public void setAuthor_id(Integer value);

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    @NotNull
    public Integer getAuthor_id();

    /**
     * Setter for <code>public.book.genre</code>.
     */
    public void setGenre(String value);

    /**
     * Getter for <code>public.book.genre</code>.
     */
    @Size(max = 50)
    public String getGenre();

    /**
     * Setter for <code>public.book.publication_date</code>.
     */
    public void setPublication_date(LocalDate value);

    /**
     * Getter for <code>public.book.publication_date</code>.
     */
    public LocalDate getPublication_date();

    /**
     * Setter for <code>public.book.isbn</code>.
     */
    public void setIsbn(String value);

    /**
     * Getter for <code>public.book.isbn</code>.
     */
    @Size(max = 20)
    public String getIsbn();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IBook
     */
    public void from(IBook from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IBook
     */
    public <E extends IBook> E into(E into);
}
