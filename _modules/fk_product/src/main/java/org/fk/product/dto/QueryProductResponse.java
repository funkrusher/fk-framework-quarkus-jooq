package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.fk.database1.testshop2.tables.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record QueryProductResponse(
    @NotNull List<ProductResponse> products,
    @NotNull Integer count,
    String localizationTest,
    boolean isLastPage,
    Long nextSeek
) {
}