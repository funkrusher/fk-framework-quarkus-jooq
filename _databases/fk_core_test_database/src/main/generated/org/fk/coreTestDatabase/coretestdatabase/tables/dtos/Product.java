/*
 * This file is generated by jOOQ.
 */
package org.fk.coreTestDatabase.coretestdatabase.tables.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fk.core.dto.AbstractDTO;
import org.fk.coreTestDatabase.coretestdatabase.tables.interfaces.IProduct;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Product extends AbstractDTO implements IProduct {

    private static final long serialVersionUID = 1L;

    private Long productId;
    private Integer clientId;
    private BigDecimal price;
    private String typeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;

    public Product() {}

    public Product(IProduct value) {
        this.productId = value.getProductId();
        this.clientId = value.getClientId();
        this.price = value.getPrice();
        this.typeId = value.getTypeId();
        this.createdAt = value.getCreatedAt();
        this.updatedAt = value.getUpdatedAt();
        this.deleted = value.getDeleted();
    }

    public Product(
        Long productId,
        Integer clientId,
        BigDecimal price,
        String typeId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean deleted
    ) {
        this.productId = productId;
        this.clientId = clientId;
        this.price = price;
        this.typeId = typeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    /**
     * Getter for <code>coreTestDatabase.product.productId</code>.
     */
    @Override
    public Long getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>coreTestDatabase.product.productId</code>.
     */
    @Override
    public void setProductId(Long productId) {
        this.productId = productId;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.clientId</code>.
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>coreTestDatabase.product.clientId</code>.
     */
    @Override
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.price</code>.
     */
    @NotNull
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>coreTestDatabase.product.price</code>.
     */
    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.typeId</code>.
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getTypeId() {
        return this.typeId;
    }

    /**
     * Setter for <code>coreTestDatabase.product.typeId</code>.
     */
    @Override
    public void setTypeId(String typeId) {
        this.typeId = typeId;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.createdAt</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>coreTestDatabase.product.createdAt</code>.
     */
    @Override
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.updatedAt</code>.
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>coreTestDatabase.product.updatedAt</code>.
     */
    @Override
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.touch();
    }

    /**
     * Getter for <code>coreTestDatabase.product.deleted</code>.
     */
    @Override
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>coreTestDatabase.product.deleted</code>.
     */
    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
        this.touch();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Product other = (Product) obj;
        if (this.productId == null) {
            if (other.productId != null)
                return false;
        }
        else if (!this.productId.equals(other.productId))
            return false;
        if (this.clientId == null) {
            if (other.clientId != null)
                return false;
        }
        else if (!this.clientId.equals(other.clientId))
            return false;
        if (this.price == null) {
            if (other.price != null)
                return false;
        }
        else if (!this.price.equals(other.price))
            return false;
        if (this.typeId == null) {
            if (other.typeId != null)
                return false;
        }
        else if (!this.typeId.equals(other.typeId))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        if (this.deleted == null) {
            if (other.deleted != null)
                return false;
        }
        else if (!this.deleted.equals(other.deleted))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.productId == null) ? 0 : this.productId.hashCode());
        result = prime * result + ((this.clientId == null) ? 0 : this.clientId.hashCode());
        result = prime * result + ((this.price == null) ? 0 : this.price.hashCode());
        result = prime * result + ((this.typeId == null) ? 0 : this.typeId.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.deleted == null) ? 0 : this.deleted.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product (");

        sb.append(productId);
        sb.append(", ").append(clientId);
        sb.append(", ").append(price);
        sb.append(", ").append(typeId);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(deleted);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IProduct from) {
        setProductId(from.getProductId());
        setClientId(from.getClientId());
        setPrice(from.getPrice());
        setTypeId(from.getTypeId());
        setCreatedAt(from.getCreatedAt());
        setUpdatedAt(from.getUpdatedAt());
        setDeleted(from.getDeleted());
    }

    @Override
    public <E extends IProduct> E into(E into) {
        into.from(this);
        return into;
    }
}
