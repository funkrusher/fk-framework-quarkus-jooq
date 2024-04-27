/*
 * This file is generated by jOOQ.
 */
package org.fk.coreTestDatabase.coretestdatabase.tables.interfaces;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;


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
     * Setter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.ID</code>.
     */
    public IDatabasechangeloglock setID(Integer value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.ID</code>.
     */
    @NotNull
    public Integer getID();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    public IDatabasechangeloglock setLOCKED(Byte value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @NotNull
    public Byte getLOCKED();

    /**
     * Setter for
     * <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public IDatabasechangeloglock setLOCKGRANTED(LocalDateTime value);

    /**
     * Getter for
     * <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    public LocalDateTime getLOCKGRANTED();

    /**
     * Setter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    public IDatabasechangeloglock setLOCKEDBY(String value);

    /**
     * Getter for <code>coreTestDatabase.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Size(max = 255)
    public String getLOCKEDBY();

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
