package org.fk.database2.public_.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.database2.public_.tables.interfaces.IBook;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BookDto implements IBook, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer book_id;
    private String title;
    private Integer author_id;
    private String genre;
    private LocalDate publication_date;
    private String isbn;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public BookDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
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
    public BookDto setBook_id(Integer book_id) {
        this.book_id = book_id;
        this.keeper.touch("book_id");
        return this;
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
    public BookDto setTitle(String title) {
        this.title = title;
        this.keeper.touch("title");
        return this;
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
    public BookDto setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        this.keeper.touch("author_id");
        return this;
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
    public BookDto setGenre(String genre) {
        this.genre = genre;
        this.keeper.touch("genre");
        return this;
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
    public BookDto setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
        this.keeper.touch("publication_date");
        return this;
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
    public BookDto setIsbn(String isbn) {
        this.isbn = isbn;
        this.keeper.touch("isbn");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // ToString, Equals, HashCode
    // -------------------------------------------------------------------------
 
    @Override
    public String toString() {
        return keeper.touchedToString();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final DTO other = (DTO) obj;
        return this.keeper.touchedEquals(other);
    }
 
    @Override
    public int hashCode() {
        return this.keeper.touchedHashCode();
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

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
