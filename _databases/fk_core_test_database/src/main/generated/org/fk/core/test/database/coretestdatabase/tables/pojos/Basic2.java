/*
 * This file is generated by jOOQ.
 */
package org.fk.core.test.database.coretestdatabase.tables.pojos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic2;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Basic2 implements IBasic2 {

    private static final long serialVersionUID = 1L;

    private UUID uuidId;
    private String string1;
    private String string2;
    private Integer clientId;

    public Basic2() {}

    public Basic2(IBasic2 value) {
        this.uuidId = value.getUuidId();
        this.string1 = value.getString1();
        this.string2 = value.getString2();
        this.clientId = value.getClientId();
    }

    public Basic2(
        UUID uuidId,
        String string1,
        String string2,
        Integer clientId
    ) {
        this.uuidId = uuidId;
        this.string1 = string1;
        this.string2 = string2;
        this.clientId = clientId;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    @NotNull
    @Override
    public UUID getUuidId() {
        return this.uuidId;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.uuidId</code>.
     */
    @Override
    public Basic2 setUuidId(UUID uuidId) {
        this.uuidId = uuidId;
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    @Size(max = 50)
    @Override
    public String getString1() {
        return this.string1;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.string1</code>.
     */
    @Override
    public Basic2 setString1(String string1) {
        this.string1 = string1;
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    @Size(max = 50)
    @Override
    public String getString2() {
        return this.string2;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.string2</code>.
     */
    @Override
    public Basic2 setString2(String string2) {
        this.string2 = string2;
        return this;
    }

    /**
     * Getter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>coreTestDatabase.Basic2.clientId</code>.
     */
    @Override
    public Basic2 setClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Basic2 (");

        sb.append(uuidId);
        sb.append(", ").append(string1);
        sb.append(", ").append(string2);
        sb.append(", ").append(clientId);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IBasic2 from) {
        setUuidId(from.getUuidId());
        setString1(from.getString1());
        setString2(from.getString2());
        setClientId(from.getClientId());
    }

    @Override
    public <E extends IBasic2> E into(E into) {
        into.from(this);
        return into;
    }
}
