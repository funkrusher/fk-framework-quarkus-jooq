package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fk.database1.testshop2.tables.dtos.ProductLangDto;
import org.fk.database1.testshop2.tables.interfaces.IProduct;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record1;

/**
 * ProductLangDTO
 */
public class ProductLangDTO extends ProductLangDto<ProductLangDTO> {

    // -------------------------------------------------------------------------
    // Constructor(s)
    // -------------------------------------------------------------------------

    public ProductLangDTO() {
        super();
    }

    public ProductLangDTO(IProductLang value) {
        super(value);
    }

    public static ProductLangDTO create(Record1<ProductLangRecord> r) {
        return new ProductLangDTO(r.value1());
    }
}