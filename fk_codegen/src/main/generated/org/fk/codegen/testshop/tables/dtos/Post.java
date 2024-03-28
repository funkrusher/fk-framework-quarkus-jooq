/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import org.fk.codegen.dto.AbstractDTO;
import org.fk.codegen.testshop.tables.interfaces.IPost;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Post extends AbstractDTO implements IPost {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String title;

    public Post() {}

    public Post(IPost value) {
        this.id = value.getId();
        this.title = value.getTitle();
    }

    public Post(
        UUID id,
        String title
    ) {
        this.id = id;
        this.title = title;
    }

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
    public void setId(UUID id) {
        this.id = id;
        this.touch();
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
    public void setTitle(String title) {
        this.title = title;
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
        final Post other = (Post) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        }
        else if (!this.title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Post (");

        sb.append(id);
        sb.append(", ").append(title);

        sb.append(")");
        return sb.toString();
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
}
