package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record3;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO implements IProduct {
    @NotNull Long productId;
    @NotNull Integer clientId;
    @NotNull BigDecimal price;
    @NotNull @Size(max = 255) String typeId;
    @NotNull LocalDateTime createdAt;
    @NotNull LocalDateTime updatedAt;
    @NotNull Boolean deleted;
    Integer creatorId;
    UserResponse creator;
    List<ProductLangResponse> langs;

    public ProductDTO(IProduct from) {
        this.from(from);
    }

    public static ProductDTO create(Record3<ProductRecord, UserResponse, List<ProductLangResponse>> rec) {
        return new ProductDTO(rec.value1())
            .setCreator(rec.value2())
            .setLangs(rec.value3());
    }

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