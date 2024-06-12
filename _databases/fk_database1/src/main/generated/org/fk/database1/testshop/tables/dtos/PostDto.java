package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import org.fk.database1.testshop.tables.interfaces.IPost;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PostDto implements IPost, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private UUID id;
    private String title;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public PostDto() {}

    public PostDto(IPost value) {
        this.setId(value.getId());
        this.setTitle(value.getTitle());
    }

    public PostDto(
        UUID id,
        String title
    ) {
        this.setId(id);
        this.setTitle(title);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.post.id</code>.
     */
    @NotNull
    @Override
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>testshop.post.id</code>.
     */
    @Override
    public PostDto setId(UUID id) {
        this.id = id;
        this.keeper.touch("id");
        return this;
    }

    /**
     * Getter for <code>testshop.post.title</code>.
     */
    @Size(max = 255)
    @Override
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>testshop.post.title</code>.
     */
    @Override
    public PostDto setTitle(String title) {
        this.title = title;
        this.keeper.touch("title");
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
    public void from(IPost from) {
        setId(from.getId());
        setTitle(from.getTitle());
    }
    @Override
    public <E extends IPost> E into(E into) {
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
