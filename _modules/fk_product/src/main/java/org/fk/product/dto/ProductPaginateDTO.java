package org.fk.product.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.product.repository.ProductRepository;

import java.util.List;

@Schema(name = "ProductPaginate", description = "Represents the pagination result of products")

public class ProductPaginateDTO {

    private List<ProductRepository.ProductItem> products;

    private Integer count;

    private String localizationTest;

    public ProductPaginateDTO() {
        super();
    }

    public List<ProductRepository.ProductItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRepository.ProductItem> products) {
        this.products = products;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getLocalizationTest() {
        return localizationTest;
    }

    public void setLocalizationTest(String localizationTest) {
        this.localizationTest = localizationTest;
    }
}
