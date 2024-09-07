package org.fk.database1.testshop2.tables.dtos;

import org.fk.core.dto.BookKeeper;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fk.core.dto.AbstractDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fk.database1.testshop2.tables.interfaces.IProduct;

/**
 * Client-specific Products
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class ProductDto<T extends ProductDto> extends AbstractDTO implements IProduct {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------
 
    private Long productId;
    private Integer clientId;
    private BigDecimal price;
    private String typeId;
    @Schema(readOnly = true, example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @Schema(readOnly = true, example = "1618312800000", type = SchemaType.NUMBER, format = "date-time", description = "Timestamp in milliseconds since 1970-01-01T00:00:00Z")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
    @Schema(readOnly = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean deleted;
    private Integer creatorId;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------
 
    public ProductDto() {}

    public ProductDto(IProduct value) { this.from(value); }

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
    public T setProductId(Long productId) {
        this.productId = productId;
        this.keeper.touch("productId");
        return (T) this;
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
    public T setClientId(Integer clientId) {
        this.clientId = clientId;
        this.keeper.touch("clientId");
        return (T) this;
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
    public T setPrice(BigDecimal price) {
        this.price = price;
        this.keeper.touch("price");
        return (T) this;
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
    public T setTypeId(String typeId) {
        this.typeId = typeId;
        this.keeper.touch("typeId");
        return (T) this;
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
    public T setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.keeper.touch("createdAt");
        return (T) this;
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
    public T setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        this.keeper.touch("updatedAt");
        return (T) this;
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
    public T setDeleted(Boolean deleted) {
        this.deleted = deleted;
        this.keeper.touch("deleted");
        return (T) this;
    }

    /**
     * Getter for <code>testshop2.product.creatorId</code>.
     */
    @Override
    public Integer getCreatorId() {
        return this.creatorId;
    }

    /**
     * Setter for <code>testshop2.product.creatorId</code>.
     */
    @Override
    public T setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
        this.keeper.touch("creatorId");
        return (T) this;
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
        setCreatorId(from.getCreatorId());
    }

    @Override
    public <E extends IProduct> E into(E into) {
        into.from(this);
        return into;
    }

}
