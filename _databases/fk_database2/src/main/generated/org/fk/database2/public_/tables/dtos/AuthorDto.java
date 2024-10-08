package org.fk.database2.public_.tables.dtos;

import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.fk.database2.public_.tables.interfaces.IAuthor;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class AuthorDto<T extends AuthorDto> extends AbstractDTO implements IAuthor {

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
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public AuthorDto() {}

    public AuthorDto(IAuthor value) { this.from(value); }

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
    public T setAuthor_id(Integer author_id) {
        this.author_id = author_id;
        this.keeper.touch("author_id");
        return (T) this;
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
    public T setName(String name) {
        this.name = name;
        this.keeper.touch("name");
        return (T) this;
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
    public T setNationality(String nationality) {
        this.nationality = nationality;
        this.keeper.touch("nationality");
        return (T) this;
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
    public T setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
        this.keeper.touch("birth_date");
        return (T) this;
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
    public T setBiography(String biography) {
        this.biography = biography;
        this.keeper.touch("biography");
        return (T) this;
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

}
