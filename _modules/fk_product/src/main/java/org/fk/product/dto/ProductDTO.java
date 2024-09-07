package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.fk.database1.testshop.tables.interfaces.IClient;
import org.fk.database1.testshop.tables.records.ClientRecord;
import org.fk.database1.testshop2.tables.dtos.ProductDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.type.ProductTypeId;
import org.jooq.Record1;
import org.jooq.Record3;
import java.util.List;

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