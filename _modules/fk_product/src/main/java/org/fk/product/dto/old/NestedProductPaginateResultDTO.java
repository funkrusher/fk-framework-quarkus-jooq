package org.fk.product.dto.old;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "NestedProductPaginateResultDTO", description = "Represents the pagination result of products")
public class NestedProductPaginateResultDTO {

    private List<NestedProductDTO> products;

    private Integer count;

    private String localizationTest;

    public NestedProductPaginateResultDTO() {
        super();
    }

    public List<NestedProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<NestedProductDTO> products) {
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
