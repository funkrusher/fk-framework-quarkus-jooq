package org.fk.product.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.codegen.dto.AbstractDTO;

import java.util.List;

@Schema(name = "ProductPaginate", description = "Represents the pagination result of products")

public class ProductPaginateDTO extends AbstractDTO {

    private List<ProductDTO> products;

    private Integer count;

    public ProductPaginateDTO() {
        super();
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
        touch();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
        touch();
    }
}
