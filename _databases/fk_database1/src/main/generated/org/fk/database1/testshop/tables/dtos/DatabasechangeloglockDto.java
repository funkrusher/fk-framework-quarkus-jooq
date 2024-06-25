package org.fk.database1.testshop.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import org.fk.database1.testshop.tables.interfaces.IDatabasechangeloglock;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class DatabasechangeloglockDto implements IDatabasechangeloglock, DTO {

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
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public DatabasechangeloglockDto() {}

    public static DatabasechangeloglockDto create(
        Integer ID,
        Boolean LOCKED,
        LocalDateTime LOCKGRANTED,
        String LOCKEDBY
    ) {
        return new DatabasechangeloglockDto()
            .setID(ID)
            .setLOCKED(LOCKED)
            .setLOCKGRANTED(LOCKGRANTED)
            .setLOCKEDBY(LOCKEDBY);
    }

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
    public DatabasechangeloglockDto setID(Integer ID) {
        this.ID = ID;
        this.keeper.touch("ID");
        return this;
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
    public DatabasechangeloglockDto setLOCKED(Boolean LOCKED) {
        this.LOCKED = LOCKED;
        this.keeper.touch("LOCKED");
        return this;
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
    public DatabasechangeloglockDto setLOCKGRANTED(LocalDateTime LOCKGRANTED) {
        this.LOCKGRANTED = LOCKGRANTED;
        this.keeper.touch("LOCKGRANTED");
        return this;
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
    public DatabasechangeloglockDto setLOCKEDBY(String LOCKEDBY) {
        this.LOCKEDBY = LOCKEDBY;
        this.keeper.touch("LOCKEDBY");
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
