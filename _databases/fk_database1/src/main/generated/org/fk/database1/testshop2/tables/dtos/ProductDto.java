package org.fk.database1.testshop2.tables.pojos;

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

import org.fk.database1.testshop2.tables.interfaces.IProduct;

/**
 * Client-specific Products
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProductDto implements IProduct, DTO {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Long productId;
    private Integer clientId;
    private BigDecimal price;
    private String typeId;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime createdAt;
    @Schema(example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    private LocalDateTime updatedAt;
    private Boolean deleted;

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
 
    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public ProductDto() { this.keeper = new BookKeeper(this); }

    // -------------------------------------------------------------------------
    // Database-Fields Setters/Getters
    // -------------------------------------------------------------------------
 
    /**
     * Getter for <code>testshop2.product.productId</code>. productId
     */
    @Override
    public Long getProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>testshop2.product.productId</code>. productId
     */
    @Override
    public ProductDto setProductId(Long productId) {
        this.productId = productId;
        this.keeper.touch("productId");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.clientId</code>. clientId
     */
    @NotNull
    @Override
    public Integer getClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>testshop2.product.clientId</code>. clientId
     */
    @Override
    public ProductDto setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.price</code>. price of the product in
     * Euro
     */
    @NotNull
    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    /**
     * Setter for <code>testshop2.product.price</code>. price of the product in
     * Euro
     */
    @Override
    public ProductDto setPrice(BigDecimal price) {
        this.price = price;
        this.keeper.touch("price");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.typeId</code>. typeId, enumeration -
     * one of: books,...
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String getTypeId() {
        return this.typeId;
    }

    /**
     * Setter for <code>testshop2.product.typeId</code>. typeId, enumeration -
     * one of: books,...
     */
    @Override
    public ProductDto setTypeId(String typeId) {
        this.typeId = typeId;
        this.keeper.touch("typeId");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.createdAt</code>.
     */
    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>testshop2.product.createdAt</code>.
     */
    @Override
    public ProductDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.keeper.touch("createdAt");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.updatedAt</code>.
     */
    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>testshop2.product.updatedAt</code>.
     */
    @Override
    public ProductDto setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.keeper.touch("updatedAt");
        return this;
    }

    /**
     * Getter for <code>testshop2.product.deleted</code>. if this product is
     * marked as deleted
     */
    @Override
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>testshop2.product.deleted</code>. if this product is
     * marked as deleted
     */
    @Override
    public ProductDto setDeleted(Boolean deleted) {
        this.deleted = deleted;
        this.keeper.touch("deleted");
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
