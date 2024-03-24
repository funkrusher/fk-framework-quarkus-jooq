/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.interfaces;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Valid
@Entity
@Table(
    name = "client",
    schema = "testshop"
)
public interface IClient extends Serializable {

    /**
     * Setter for <code>testshop.client.clientId</code>.
     */
    public void setClientId(Integer value);

    /**
     * Getter for <code>testshop.client.clientId</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    public Integer getClientId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common
     * interface IClient
     */
    public void from(IClient from);

    /**
     * Copy data into another generated Record/POJO implementing the common
     * interface IClient
     */
    public <E extends IClient> E into(E into);
}
