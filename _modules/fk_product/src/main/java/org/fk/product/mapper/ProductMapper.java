package org.fk.product.mapper;

import org.fk.database1.testshop.tables.records.LangRecord;
import org.fk.database1.testshop2.tables.ProductLang;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dto.CreateProductRequest;
import org.fk.product.dto.LangResponse;
import org.fk.product.dto.ProductLangResponse;
import org.jooq.Record1;
import org.jooq.Record2;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // -------------------------------------------------------------------------
    // Target: jOOQ-Record, Source: DTO
    // -------------------------------------------------------------------------

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductRecord map(CreateProductRequest createProductRequest);

    // -------------------------------------------------------------------------
    // Target: DTO, Source: jOOQ-Record
    // -------------------------------------------------------------------------

    @Mapping(target = "lang", ignore = true)
    ProductLangResponse map(ProductLangRecord rec);

    LangResponse map(LangRecord rec);

    // -------------------------------------------------------------------------
    // Jooq-Specific Converters
    // -------------------------------------------------------------------------

    default ProductLangResponse convertFrom(Record2<ProductLangRecord, LangResponse> rec) {
        return map(rec.value1())
            .setLang(rec.value2());
    }

    default LangResponse convertFrom(Record1<LangRecord> rec) {
        return map(rec.value1());
    }
}
