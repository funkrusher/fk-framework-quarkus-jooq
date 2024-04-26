/*
 * This file is generated by jOOQ.
 */
package org.fk.database1.testshop.tables.interfaces;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IPost extends Serializable {

    /**
     * Setter for <code>testshop.post.id</code>.
     */
    public void setId(UUID value);

    /**
     * Getter for <code>testshop.post.id</code>.
     */
    @NotNull
    public UUID getId();

    /**
     * Setter for <code>testshop.post.title</code>.
     */
    public void setTitle(String value);

    /**
     * Getter for <code>testshop.post.title</code>.
     */
    @Size(max = 255)
    public String getTitle();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IPost
     */
    public void from(IPost from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IPost
     */
    public <E extends IPost> E into(E into);
}
