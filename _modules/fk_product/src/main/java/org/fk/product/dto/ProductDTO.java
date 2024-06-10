package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop2.tables.dtos.ProductDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.product.type.ProductTypeId;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Client-specific Products
 */
public class ProductDTO implements IProduct, DTO {

    @Serial
    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    private Long productId;
    private Integer clientId;
    private BigDecimal price;
    @Schema(example = "book", type = SchemaType.STRING, format = "string", description = "type of product (one of: book, ...)")
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

    @Schema(writeOnly = true)
    private Boolean deleteFlag;

    @Schema(readOnly = true)
    private ProductLangDTO lang;

    private List<ProductLangDTO> langs;

    @Schema(readOnly = true)
    private ProductLangDTO mylang;

    @Schema(hidden = true)
    private ProductTypeId productTypeId;

    @Schema(hidden = true)
    private UserDTO creator;

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductDTO() { this.keeper = new BookKeeper(this); }

    public ProductDTO(
            Long productId,
            Integer clientId,
            BigDecimal price,
            String typeId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean deleted,
            Integer creatorId
    ) {
        this.productId = productId;
        this.clientId = clientId;
        this.price = price;
        this.typeId = typeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.creatorId = creatorId;
    }


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
    public ProductDTO setProductId(Long productId) {
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
    public ProductDTO setClientId(Integer clientId) {
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
    public ProductDTO setPrice(BigDecimal price) {
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
    public ProductDTO setTypeId(String typeId) {
        this.productTypeId = ProductTypeId.fromValue(typeId);
        this.typeId = typeId;
        this.keeper.touch("productTypeId");
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
    public ProductDTO setCreatedAt(LocalDateTime createdAt) {
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
    public ProductDTO setUpdatedAt(LocalDateTime updatedAt) {
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
    public ProductDTO setDeleted(Boolean deleted) {
        this.deleted = deleted;
        this.keeper.touch("deleted");
        return this;
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
    public ProductDTO setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
        this.keeper.touch("creatorId");
        return this;
    }

    // -------------------------------------------------------------------------
    // Non-Database-Fields Setters/Getters (please define here)
    // -------------------------------------------------------------------------

    @JsonIgnore
    public void setCreator(UserDTO creator) {
        this.creator = creator;
        keeper.touch("creator");
    }

    @JsonProperty
    public UserDTO getCreator() {
        return creator;
    }

    @JsonIgnore
    public ProductTypeId getProductTypeId() {
        return productTypeId;
    }

    @JsonIgnore
    public void setProductTypeId(ProductTypeId productTypeId) {
        this.productTypeId = productTypeId;
        this.typeId = productTypeId != null ? productTypeId.getValue() : null;
        this.keeper.touch("productTypeId");
        this.keeper.touch("typeId");
    }

    @JsonProperty
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        this.keeper.touch("deleteFlag");
    }

    @JsonIgnore
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    @JsonIgnore
    public void setMylang(ProductLangDTO lang) {
        this.mylang = mylang;
        keeper.touch("mylang");
    }

    @JsonProperty
    public ProductLangDTO getMylang() {
        return mylang;
    }


    @JsonIgnore
    public void setLang(ProductLangDTO lang) {
        this.lang = lang;
        keeper.touch("lang");
    }

    @JsonProperty
    public ProductLangDTO getLang() {
        return lang;
    }

    public List<ProductLangDTO> getLangs() {
        return langs;
    }

    public void setLangs(List<ProductLangDTO> langs) {
        this.langs = langs;
        keeper.touch("langs");
    }

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
    protected transient BookKeeper keeper;

    @JsonIgnore
    @XmlTransient
    public BookKeeper getBookKeeper() {
        return keeper;
    }
}
