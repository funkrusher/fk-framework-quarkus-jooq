/*
 * This file is generated by jOOQ.
 */
package org.fk.codegen.testshop.tables.records;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fk.codegen.testshop.tables.Product;
import org.fk.codegen.testshop.tables.interfaces.IProduct;
import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
@Valid
@Entity
@Table(
    name = "product",
    schema = "testshop"
)
public class ProductRecord extends UpdatableRecordImpl<ProductRecord> implements IProduct {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>testshop.product.productId</code>.
     */
    @Override
    public void setProductId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>testshop.product.productId</code>.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    @Override
    public Long getProductId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>testshop.product.clientId</code>.
     */
    @Override
    public void setClientId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>testshop.product.clientId</code>.
     */
    @Column(name = "clientId", nullable = false)
    @NotNull
    @Override
    public Integer getClientId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>testshop.product.price</code>.
     */
    @Override
    public void setPrice(BigDecimal value) {
        set(2, value);
    }

    /**
     * Getter for <code>testshop.product.price</code>.
     */
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @NotNull
    @Override
    public BigDecimal getPrice() {
        return (BigDecimal) get(2);
    }

    /**
     * Setter for <code>testshop.product.createdAt</code>.
     */
    @Override
    public void setCreatedAt(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>testshop.product.createdAt</code>.
     */
    @Column(name = "createdAt")
    @Override
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>testshop.product.updatedAt</code>.
     */
    @Override
    public void setUpdatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>testshop.product.updatedAt</code>.
     */
    @Column(name = "updatedAt")
    @Override
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(4);
    }

    /**
     * Setter for <code>testshop.product.deleted</code>.
     */
    @Override
    public void setDeleted(Boolean value) {
        set(5, value);
    }

    /**
     * Getter for <code>testshop.product.deleted</code>.
     */
    @Column(name = "deleted")
    @Override
    public Boolean getDeleted() {
        return (Boolean) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IProduct from) {
        setProductId(from.getProductId());
        setClientId(from.getClientId());
        setPrice(from.getPrice());
        setCreatedAt(from.getCreatedAt());
        setUpdatedAt(from.getUpdatedAt());
        setDeleted(from.getDeleted());
        resetChangedOnNotNull();
    }

    @Override
    public <E extends IProduct> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProductRecord
     */
    public ProductRecord() {
        super(Product.PRODUCT);
    }

    /**
     * Create a detached, initialised ProductRecord
     */
    public ProductRecord(Long productId, Integer clientId, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleted) {
        super(Product.PRODUCT);

        setProductId(productId);
        setClientId(clientId);
        setPrice(price);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeleted(deleted);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised ProductRecord
     */
    public ProductRecord(org.fk.codegen.testshop.tables.dtos.Product value) {
        super(Product.PRODUCT);

        if (value != null) {
            setProductId(value.getProductId());
            setClientId(value.getClientId());
            setPrice(value.getPrice());
            setCreatedAt(value.getCreatedAt());
            setUpdatedAt(value.getUpdatedAt());
            setDeleted(value.getDeleted());
            resetChangedOnNotNull();
        }
    }
}
