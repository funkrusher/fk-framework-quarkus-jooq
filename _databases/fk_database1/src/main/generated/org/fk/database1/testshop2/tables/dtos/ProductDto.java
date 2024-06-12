package org.fk.database1.testshop2.tables.dtos;

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
    private Integer creatorId;

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
    public Long ProductId() {
        return this.productId;
    }

    /**
     * Setter for <code>testshop2.product.productId</code>. productId
     */
    @Override
    public Product ProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.clientId</code>. clientId
     */
    @NotNull
    @Override
    public Integer ClientId() {
        return this.clientId;
    }

    /**
     * Setter for <code>testshop2.product.clientId</code>. clientId
     */
    @Override
    public Product ClientId(Integer clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.price</code>. price of the product in
     * Euro
     */
    @NotNull
    @Override
    public BigDecimal Price() {
        return this.price;
    }

    /**
     * Setter for <code>testshop2.product.price</code>. price of the product in
     * Euro
     */
    @Override
    public Product Price(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.typeId</code>. typeId, enumeration -
     * one of: books,...
     */
    @NotNull
    @Size(max = 255)
    @Override
    public String TypeId() {
        return this.typeId;
    }

    /**
     * Setter for <code>testshop2.product.typeId</code>. typeId, enumeration -
     * one of: books,...
     */
    @Override
    public Product TypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.createdAt</code>.
     */
    @Override
    public LocalDateTime CreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>testshop2.product.createdAt</code>.
     */
    @Override
    public Product CreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.updatedAt</code>.
     */
    @Override
    public LocalDateTime UpdatedAt() {
        return this.updatedAt;
    }

    /**
     * Setter for <code>testshop2.product.updatedAt</code>.
     */
    @Override
    public Product UpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.deleted</code>. if this product is
     * marked as deleted
     */
    @Override
    public Boolean Deleted() {
        return this.deleted;
    }

    /**
     * Setter for <code>testshop2.product.deleted</code>. if this product is
     * marked as deleted
     */
    @Override
    public Product Deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    /**
     * Getter for <code>testshop2.product.creatorId</code>.
     */
    @Override
    public Integer CreatorId() {
        return this.creatorId;
    }

    /**
     * Setter for <code>testshop2.product.creatorId</code>.
     */
    @Override
    public Product CreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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
        ProductId(from.ProductId());
        ClientId(from.ClientId());
        Price(from.Price());
        TypeId(from.TypeId());
        CreatedAt(from.CreatedAt());
        UpdatedAt(from.UpdatedAt());
        Deleted(from.Deleted());
        CreatorId(from.CreatorId());
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
    protected transient BookKeeper keeper;
 
    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
