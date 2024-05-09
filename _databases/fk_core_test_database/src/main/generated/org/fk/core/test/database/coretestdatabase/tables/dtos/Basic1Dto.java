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

import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic1;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Basic1Dto implements IBasic1, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer autoIncId;
    private String string1;
    private String string2;
    private Integer integer1;
    private Long long1;
    private BigDecimal decimal1;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime dateTime1;
    private Integer clientId;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public Basic1Dto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    @Override
    public Integer getAutoIncId() {
        return this.autoIncId;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.autoIncId</code>.
     */
    @Override
    public Basic1Dto setAutoIncId(Integer autoIncId) {
        this.autoIncId = autoIncId;
        this.keeper.touch("autoIncId");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    @Size(max = 50)
    @Override
    public String getString1() {
        return this.string1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.string1</code>.
     */
    @Override
    public Basic1Dto setString1(String string1) {
        this.string1 = string1;
        this.keeper.touch("string1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    @Size(max = 50)
    @Override
    public String getString2() {
        return this.string2;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.string2</code>.
     */
    @Override
    public Basic1Dto setString2(String string2) {
        this.string2 = string2;
        this.keeper.touch("string2");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    @Override
    public Integer getInteger1() {
        return this.integer1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.integer1</code>.
     */
    @Override
    public Basic1Dto setInteger1(Integer integer1) {
        this.integer1 = integer1;
        this.keeper.touch("integer1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    @Override
    public Long getLong1() {
        return this.long1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.long1</code>.
     */
    @Override
    public Basic1Dto setLong1(Long long1) {
        this.long1 = long1;
        this.keeper.touch("long1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    @Override
    public BigDecimal getDecimal1() {
        return this.decimal1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.decimal1</code>.
     */
    @Override
    public Basic1Dto setDecimal1(BigDecimal decimal1) {
        this.decimal1 = decimal1;
        this.keeper.touch("decimal1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    @Override
    public LocalDateTime getDateTime1() {
        return this.dateTime1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.dateTime1</code>.
     */
    @Override
    public Basic1Dto setDateTime1(LocalDateTime dateTime1) {
        this.dateTime1 = dateTime1;
        this.keeper.touch("dateTime1");
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic1.clientId</code>.
     */
    @Override
    public Basic1Dto setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
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
    public void from(IBasic1 from) {
        setAutoIncId(from.getAutoIncId());
        setString1(from.getString1());
        setString2(from.getString2());
        setInteger1(from.getInteger1());
        setLong1(from.getLong1());
        setDecimal1(from.getDecimal1());
        setDateTime1(from.getDateTime1());
        setClientId(from.getClientId());
    }
    @Override
    public <E extends IBasic1> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected transient BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
