package org.fk.product.dto.old;

import org.fk.database1.testshop2.tables.dtos.ProductLangDto;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
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