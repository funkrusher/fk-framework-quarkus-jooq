package org.fk.database1.testshop.tables.pojos;

import org.fk.core.dto.DTO;
import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.fk.database1.testshop.tables.interfaces.IUser;

/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UserDto implements IUser, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Integer userId;
    private Integer clientId;
    private String email;
    private String firstname;
    private String lastname;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public UserDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop.user.userId</code>.
     */
    @Override
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>testshop.user.userId</code>.
     */
    @Override
    public UserDto setUserId(Integer userId) {
        this.userId = userId;
        this.keeper.touch("userId");
        return this;
    }

    /**
     * Getter for <code>testshop.user.clientId</code>.
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>testshop.user.clientId</code>.
     */
    @Override
    public UserDto setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
        return this;
    }

    /**
     * Getter for <code>testshop.user.email</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for <code>testshop.user.email</code>.
     */
    @Override
    public UserDto setEmail(String email) {
        this.email = email;
        this.keeper.touch("email");
        return this;
    }

    /**
     * Getter for <code>testshop.user.firstname</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getFirstname() {
        return this.firstname;
    }

    /**
     * Setter for <code>testshop.user.firstname</code>.
     */
    @Override
    public UserDto setFirstname(String firstname) {
        this.firstname = firstname;
        this.keeper.touch("firstname");
        return this;
    }

    /**
     * Getter for <code>testshop.user.lastname</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Setter for <code>testshop.user.lastname</code>.
     */
    @Override
    public UserDto setLastname(String lastname) {
        this.lastname = lastname;
        this.keeper.touch("lastname");
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
    public void from(IUser from) {
        setUserId(from.getUserId());
        setClientId(from.getClientId());
        setEmail(from.getEmail());
        setFirstname(from.getFirstname());
        setLastname(from.getLastname());
    }
    @Override
    public <E extends IUser> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // BookKeeper (Patching Updates Support)
    // -------------------------------------------------------------------------
     
    @JsonIgnore
    @XmlTransient
    protected BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
