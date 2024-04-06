/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.core.dto.AbstractDTO;
import org.fk.database2.public_.tables.interfaces.IBook;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Book extends AbstractDTO implements IBook {

    private static final long serialVersionUID = 1L;

    private Integer book_id;
    private String title;
    private Integer author_id;
    private String genre;
    private LocalDate publication_date;
    private String isbn;

    public Book() {}

    public Book(IBook value) {
        this.book_id = value.getBook_id();
        this.title = value.getTitle();
        this.author_id = value.getAuthor_id();
        this.genre = value.getGenre();
        this.publication_date = value.getPublication_date();
        this.isbn = value.getIsbn();
    }

    public Book(
        Integer book_id,
        String title,
        Integer author_id,
        String genre,
        LocalDate publication_date,
        String isbn
    ) {
        this.book_id = book_id;
        this.title = title;
        this.author_id = author_id;
        this.genre = genre;
        this.publication_date = publication_date;
        this.isbn = isbn;
    }

    /**
     * Getter for <code>public.book.book_id</code>.
     */
    @Override
    public Integer getBook_id() {
        return this.book_id;
    }

    /**
     * Setter for <code>public.book.book_id</code>.
     */
    @Override
    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
        this.touch();
    }

    /**
     * Getter for <code>public.book.title</code>.
     */
    @NotNull
    @Size(max = 200)
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>public.book.title</code>.
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
        this.touch();
    }

    /**
     * Getter for <code>public.book.author_id</code>.
     */
    @NotNull
    @Override
    public Integer getAuthor_id() {
        return this.author_id;
    }

    /**
     * Setter for <code>public.book.author_id</code>.
     */
    @Override
    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        this.touch();
    }

    /**
     * Getter for <code>public.book.genre</code>.
     */
    @Size(max = 50)
    @Override
    public String getGenre() {
        return this.genre;
    }

    /**
     * Setter for <code>public.book.genre</code>.
     */
    @Override
    public void setGenre(String genre) {
        this.genre = genre;
        this.touch();
    }

    /**
     * Getter for <code>public.book.publication_date</code>.
     */
    @Override
    public LocalDate getPublication_date() {
        return this.publication_date;
    }

    /**
     * Setter for <code>public.book.publication_date</code>.
     */
    @Override
    public void setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
        this.touch();
    }

    /**
     * Getter for <code>public.book.isbn</code>.
     */
    @Size(max = 20)
    @Override
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * Setter for <code>public.book.isbn</code>.
     */
    @Override
    public void setIsbn(String isbn) {
        this.isbn = isbn;
        this.touch();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Book other = (Book) obj;
        if (this.book_id == null) {
            if (other.book_id != null)
                return false;
        }
        else if (!this.book_id.equals(other.book_id))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        }
        else if (!this.title.equals(other.title))
            return false;
        if (this.author_id == null) {
            if (other.author_id != null)
                return false;
        }
        else if (!this.author_id.equals(other.author_id))
            return false;
        if (this.genre == null) {
            if (other.genre != null)
                return false;
        }
        else if (!this.genre.equals(other.genre))
            return false;
        if (this.publication_date == null) {
            if (other.publication_date != null)
                return false;
        }
        else if (!this.publication_date.equals(other.publication_date))
            return false;
        if (this.isbn == null) {
            if (other.isbn != null)
                return false;
        }
        else if (!this.isbn.equals(other.isbn))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.book_id == null) ? 0 : this.book_id.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.author_id == null) ? 0 : this.author_id.hashCode());
        result = prime * result + ((this.genre == null) ? 0 : this.genre.hashCode());
        result = prime * result + ((this.publication_date == null) ? 0 : this.publication_date.hashCode());
        result = prime * result + ((this.isbn == null) ? 0 : this.isbn.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Book (");

        sb.append(book_id);
        sb.append(", ").append(title);
        sb.append(", ").append(author_id);
        sb.append(", ").append(genre);
        sb.append(", ").append(publication_date);
        sb.append(", ").append(isbn);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IBook from) {
        setBook_id(from.getBook_id());
        setTitle(from.getTitle());
        setAuthor_id(from.getAuthor_id());
        setGenre(from.getGenre());
        setPublication_date(from.getPublication_date());
        setIsbn(from.getIsbn());
    }

    @Override
    public <E extends IBook> E into(E into) {
        into.from(this);
        return into;
    }
}
