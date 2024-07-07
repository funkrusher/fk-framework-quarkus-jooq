package org.fk.library.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database2.public_.tables.interfaces.IAuthor;

import java.util.List;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.database2.public_.tables.interfaces.IAuthor;
import org.fk.database2.public_.tables.records.AuthorRecord;
import org.jooq.Record2;
import org.jooq.Record3;

/**
 * AuthorDTO
 */
@Schema(name = "AuthorDTO", description = "Represents an author")
public class AuthorDTO implements IAuthor, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    private Integer author_id;
    private String name;
    private String nationality;
    private LocalDate birth_date;
    private String biography;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------

    private List<BookDTO> books;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public AuthorDTO() {}

    public AuthorDTO(IAuthor value) { this.from(value); }

    public static AuthorDTO create(Record2<AuthorRecord, List<BookDTO>> r) {
        return new AuthorDTO(r.value1())
            .setBooks(r.value2());
    }


    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------

    /**
     * Getter for <code>public.author.author_id</code>.
     */
    @Override
    public Integer getAuthor_id() {
        return this.author_id;
    }

    /**
     * Setter for <code>public.author.author_id</code>.
     */
    @Override
    public AuthorDTO setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        this.keeper.touch("author_id");
        return this;
    }

    /**
     * Getter for <code>public.author.name</code>.
     */
    @NotNull
    @Size(max = 100)
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.author.name</code>.
     */
    @Override
    public AuthorDTO setName(String name) {
        this.name = name;
        this.keeper.touch("name");
        return this;
    }

    /**
     * Getter for <code>public.author.nationality</code>.
     */
    @Size(max = 50)
    @Override
    public String getNationality() {
        return this.nationality;
    }

    /**
     * Setter for <code>public.author.nationality</code>.
     */
    @Override
    public AuthorDTO setNationality(String nationality) {
        this.nationality = nationality;
        this.keeper.touch("nationality");
        return this;
    }

    /**
     * Getter for <code>public.author.birth_date</code>.
     */
    @Override
    public LocalDate getBirth_date() {
        return this.birth_date;
    }

    /**
     * Setter for <code>public.author.birth_date</code>.
     */
    @Override
    public AuthorDTO setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
        this.keeper.touch("birth_date");
        return this;
    }

    /**
     * Getter for <code>public.author.biography</code>.
     */
    @Override
    public String getBiography() {
        return this.biography;
    }

    /**
     * Setter for <code>public.author.biography</code>.
     */
    @Override
    public AuthorDTO setBiography(String biography) {
        this.biography = biography;
        this.keeper.touch("biography");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    public List<BookDTO> getBooks() {
        return books;
    }

    public AuthorDTO setBooks(List<BookDTO> books) {
        this.books = books;
        this.keeper.touch("books");
        return this;
    }

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
    public void from(IAuthor from) {
        setAuthor_id(from.getAuthor_id());
        setName(from.getName());
        setNationality(from.getNationality());
        setBirth_date(from.getBirth_date());
        setBiography(from.getBiography());
    }

    @Override
    public <E extends IAuthor> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------

    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper = new BookKeeper(this);

    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
