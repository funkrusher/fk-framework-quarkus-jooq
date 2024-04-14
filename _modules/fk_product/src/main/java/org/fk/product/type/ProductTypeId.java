package org.fk.product.type;

public enum ProductTypeId {
    CLOTHING("clothing"),
    BOOK("book"),
    TECHNOLOGY("technology");

    private final String value;

    ProductTypeId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProductTypeId fromValue(String value) {
        for (ProductTypeId type : ProductTypeId.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid product type value: " + value);
    }
}
