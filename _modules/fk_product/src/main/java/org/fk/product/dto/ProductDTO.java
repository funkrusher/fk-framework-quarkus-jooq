package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record3;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDTO implements IProduct {
    private @NotNull Long productId;
    private @NotNull Integer clientId;
    private @NotNull BigDecimal price;
    private @NotNull
    @Size(max = 255) String typeId;
    private @NotNull LocalDateTime createdAt;
    private @NotNull LocalDateTime updatedAt;
    private @NotNull Boolean deleted;
    private Integer creatorId;
    private UserResponse creator;
    private @NotNull List<ProductLangResponse> langs;

    public static ProductDTO create(Record3<ProductRecord, UserResponse, List<ProductLangResponse>> rec) {
        ProductRecord product = rec.value1();
        return new ProductDTO()
            .setProductId(product.getProductid())
            .setClientId(product.getClientid())
            .setPrice(product.getPrice())
            .setTypeId(product.getTypeid())
            .setCreatedAt(product.getCreatedat())
            .setUpdatedAt(product.getUpdatedat())
            .setDeleted(product.getDeleted())
            .setCreatorId(product.getCreatorid())
            .setCreator(rec.value2())
            .setLangs(rec.value3());
    }

}