package org.fk.product.api;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "ProductPaginate", description = "Represents the pagination result of products")
public class ProductPaginateAPI {

    private List<ProductAPI> products;

    private Integer count;

    private String localizationTest;

    public ProductPaginateAPI() {
        super();
    }

    public List<ProductAPI> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAPI> products) {
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
