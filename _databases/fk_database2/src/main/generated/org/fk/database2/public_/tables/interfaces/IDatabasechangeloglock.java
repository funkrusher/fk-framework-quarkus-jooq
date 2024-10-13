/*
 * This file is generated by jOOQ.
 */
package org.fk.database2.public_.tables.interfaces;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public interface IDatabasechangeloglock extends Serializable {

    /**
     * Setter for <code>public.databasechangeloglock.id</code>.
     */
    public IDatabasechangeloglock setId(Integer value);

    /**
     * Getter for <code>public.databasechangeloglock.id</code>.
     */
    @NotNull
    public Integer getId();

    /**
     * Setter for <code>public.databasechangeloglock.locked</code>.
     */
    public IDatabasechangeloglock setLocked(Boolean value);

    /**
     * Getter for <code>public.databasechangeloglock.locked</code>.
     */
    @NotNull
    public Boolean getLocked();

    /**
     * Setter for <code>public.databasechangeloglock.lockgranted</code>.
     */
    public IDatabasechangeloglock setLockgranted(LocalDateTime value);

    /**
     * Getter for <code>public.databasechangeloglock.lockgranted</code>.
     */
    public LocalDateTime getLockgranted();

    /**
     * Setter for <code>public.databasechangeloglock.lockedby</code>.
     */
    public IDatabasechangeloglock setLockedby(String value);

    /**
     * Getter for <code>public.databasechangeloglock.lockedby</code>.
     */
    @Size(max = 255)
    public String getLockedby();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IDatabasechangeloglock
     */
    public void from(IDatabasechangeloglock from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IDatabasechangeloglock
     */
    public <E extends IDatabasechangeloglock> E into(E into);
}
