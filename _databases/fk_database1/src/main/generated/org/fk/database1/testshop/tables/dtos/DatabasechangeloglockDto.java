package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.interfaces.IDatabasechangeloglock;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangeloglockDto<T extends DatabasechangeloglockDto> extends AbstractDTO implements IDatabasechangeloglock {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer ID;
    private Boolean LOCKED;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime LOCKGRANTED;
    private String LOCKEDBY;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public DatabasechangeloglockDto() {}

    public DatabasechangeloglockDto(IDatabasechangeloglock value) { this.from(value); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    @NotNull
    @Override
    public Integer getID() {
        return this.ID;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.ID</code>.
     */
    @Override
    public T setID(Integer ID) {
        this.ID = ID;
        this.keeper.touch("ID");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @NotNull
    @Override
    public Boolean getLOCKED() {
        return this.LOCKED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKED</code>.
     */
    @Override
    public T setLOCKED(Boolean LOCKED) {
        this.LOCKED = LOCKED;
        this.keeper.touch("LOCKED");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    @Override
    public LocalDateTime getLOCKGRANTED() {
        return this.LOCKGRANTED;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKGRANTED</code>.
     */
    @Override
    public T setLOCKGRANTED(LocalDateTime LOCKGRANTED) {
        this.LOCKGRANTED = LOCKGRANTED;
        this.keeper.touch("LOCKGRANTED");
        return (T) this;
    }

    /**
     * Getter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Size(max = 255)
    @Override
    public String getLOCKEDBY() {
        return this.LOCKEDBY;
    }

    /**
     * Setter for <code>testshop.DATABASECHANGELOGLOCK.LOCKEDBY</code>.
     */
    @Override
    public T setLOCKEDBY(String LOCKEDBY) {
        this.LOCKEDBY = LOCKEDBY;
        this.keeper.touch("LOCKEDBY");
        return (T) this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IDatabasechangeloglock from) {
        setID(from.getID());
        setLOCKED(from.getLOCKED());
        setLOCKGRANTED(from.getLOCKGRANTED());
        setLOCKEDBY(from.getLOCKEDBY());
    }

    @Override
    public <E extends IDatabasechangeloglock> E into(E into) {
        into.from(this);
        return into;
    }

}
