package org.fk.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlTransient;
import org.fk.core.dto.BookKeeper;
import org.fk.core.dto.DTO;
import org.fk.database1.testshop.tables.records.UserRoleRecord;
import org.fk.database1.testshop2.tables.interfaces.IProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.jooq.Record1;

import java.io.Serial;

/**
 * ProductLangDTO
 */
public record ProductLangDTO (
    // -------------------------------------------------------------------------
    // Database-Fields (must exist in the associated database table)
    // -------------------------------------------------------------------------

    Long productId,
    Integer langId,
    String name,
    String description,

    // -------------------------------------------------------------------------
    // Non-Database-Fields (please define your additional fields here)
    // -------------------------------------------------------------------------
    LangDTO lang
)  implements DTO {


    public static ProductLangDTO create(Record1<ProductLangRecord> r) {
        ProductLangRecord productLangRecord = r.value1();

        return new ProductLangDTO(
            productLangRecord.getProductId(),
            productLangRecord.getLangId(),
            productLangRecord.getName(),
            productLangRecord.getDescription(),
            null
        );
    }
}