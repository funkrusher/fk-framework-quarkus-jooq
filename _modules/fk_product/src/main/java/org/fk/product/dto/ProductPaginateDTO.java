package org.fk.product.dto;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "ProductPaginate", description = "Represents the pagination result of products")
public class ProductPaginateDTO {

    private String products;

    private Integer count;

    private String localizationTest;

    public ProductPaginateDTO() {
        super();
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
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
