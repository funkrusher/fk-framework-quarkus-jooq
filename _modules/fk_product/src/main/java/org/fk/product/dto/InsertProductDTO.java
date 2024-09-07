package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop2.tables.dtos.ProductDto;

/**
 * InsertProductDTO
 */
public class InsertProductDTO extends ProductDto<InsertProductDTO> {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(hidden = true)
    @Override
    public Long getProductId() {
        return super.getProductId();
    }
}