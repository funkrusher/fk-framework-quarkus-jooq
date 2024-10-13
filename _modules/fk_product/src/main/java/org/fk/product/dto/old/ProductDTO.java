package org.fk.product.dto.old;

import org.fk.database1.testshop2.tables.dtos.ProductDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record1;

/**
 * ProductDTO
 */
public class ProductDTO extends ProductDto<ProductDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductDTO() {
        super();
    }

    public ProductDTO(IProduct value) {
        super(value);
    }

    public static ProductDTO create(Record1<ProductRecord> r) {
        return new ProductDTO(r.value1());
    }
}