package org.fk.core.test.database.coretestdatabase.tables.dtos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.fk.core.test.database.coretestdatabase.tables.interfaces.INested1;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Nested1Dto implements INested1, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 

    private Integer autoIncId;
    private UUID uuidId;
    private String string1;
    private String string2;
    private Integer integer1;
    private Long long1;
    private BigDecimal decimal1;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime dateTime1;


    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public Nested1Dto() {}


    public Nested1Dto(INested1 value) {
        this.setAutoIncId(value.getAutoIncId());
        this.setUuidId(value.getUuidId());
        this.setString1(value.getString1());
        this.setString2(value.getString2());
        this.setInteger1(value.getInteger1());
        this.setLong1(value.getLong1());
        this.setDecimal1(value.getDecimal1());
        this.setDateTime1(value.getDateTime1());
    }


    public Nested1Dto(
        Integer autoIncId,
        UUID uuidId,
        String string1,
        String string2,
        Integer integer1,
        Long long1,
        BigDecimal decimal1,
        LocalDateTime dateTime1
    ) {
        this.setAutoIncId(autoIncId);
        this.setUuidId(uuidId);
        this.setString1(string1);
        this.setString2(string2);
        this.setInteger1(integer1);
        this.setLong1(long1);
        this.setDecimal1(decimal1);
        this.setDateTime1(dateTime1);
    }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 

    /**
     * Getter for <code>coreTestDatabase.Nested1.autoIncId</code>.
     */
    @NotNull
    @Override
    public Integer getAutoIncId() {
        return this.autoIncId;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.autoIncId</code>.
     */
    @Override
    public Nested1Dto setAutoIncId(Integer autoIncId) {
        this.autoIncId = autoIncId;
        this.keeper.touch("autoIncId");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.uuidId</code>.
     */
    @NotNull
    @Override
    public UUID getUuidId() {
        return this.uuidId;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.uuidId</code>.
     */
    @Override
    public Nested1Dto setUuidId(UUID uuidId) {
        this.uuidId = uuidId;
        this.keeper.touch("uuidId");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.string1</code>.
     */
    @Size(max = 50)
    @Override
    public String getString1() {
        return this.string1;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.string1</code>.
     */
    @Override
    public Nested1Dto setString1(String string1) {
        this.string1 = string1;
        this.keeper.touch("string1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.string2</code>.
     */
    @Size(max = 50)
    @Override
    public String getString2() {
        return this.string2;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.string2</code>.
     */
    @Override
    public Nested1Dto setString2(String string2) {
        this.string2 = string2;
        this.keeper.touch("string2");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.integer1</code>.
     */
    @Override
    public Integer getInteger1() {
        return this.integer1;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.integer1</code>.
     */
    @Override
    public Nested1Dto setInteger1(Integer integer1) {
        this.integer1 = integer1;
        this.keeper.touch("integer1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.long1</code>.
     */
    @Override
    public Long getLong1() {
        return this.long1;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.long1</code>.
     */
    @Override
    public Nested1Dto setLong1(Long long1) {
        this.long1 = long1;
        this.keeper.touch("long1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.decimal1</code>.
     */
    @Override
    public BigDecimal getDecimal1() {
        return this.decimal1;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.decimal1</code>.
     */
    @Override
    public Nested1Dto setDecimal1(BigDecimal decimal1) {
        this.decimal1 = decimal1;
        this.keeper.touch("decimal1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Nested1.dateTime1</code>.
     */
    @Override
    public LocalDateTime getDateTime1() {
        return this.dateTime1;
    }

    /**
     * Setter for <code>coreTestDatabase.Nested1.dateTime1</code>.
     */
    @Override
    public Nested1Dto setDateTime1(LocalDateTime dateTime1) {
        this.dateTime1 = dateTime1;
        this.keeper.touch("dateTime1");
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
    public void from(INested1 from) {
        setAutoIncId(from.getAutoIncId());
        setUuidId(from.getUuidId());
        setString1(from.getString1());
        setString2(from.getString2());
        setInteger1(from.getInteger1());
        setLong1(from.getLong1());
        setDecimal1(from.getDecimal1());
        setDateTime1(from.getDateTime1());
    }

    @Override
    public <E extends INested1> E into(E into) {
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
