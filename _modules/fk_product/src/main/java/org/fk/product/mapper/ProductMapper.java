package org.fk.product.mapper;

import org.fk.database1.testshop.tables.records.LangRecord;
import org.fk.database1.testshop.tables.records.RoleRecord;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.fk.database1.testshop2.tables.records.ProductLangRecord;
import org.fk.database1.testshop2.tables.records.ProductRecord;
import org.fk.product.dto.*;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // -------------------------------------------------------------------------
    // Target: jOOQ-Record, Source: DTO
    // -------------------------------------------------------------------------

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductRecord fromCreateProductRequest(CreateProductRequest createProductRequest);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    ProductRecord fromUpdateProductRequest(UpdateProductRequest createProductRequest);

    default ProductRecord fromPatchProductRequest(PatchProductRequest patchProductRequest, Map<String, Object> patch) {
        // seehttps://github.com/mapstruct/mapstruct/issues/2504
        // For each field in patch, set according db-record field with correctly typed value.
        final ProductRecord update = new ProductRecord();
        update.setProductId(patchProductRequest.getProductId());
        if (patch.containsKey(PatchProductRequest.Fields.price)) update.setPrice(patchProductRequest.getPrice());
        if (patch.containsKey(PatchProductRequest.Fields.clientId))
            update.setClientId(patchProductRequest.getClientId());
        if (patch.containsKey(PatchProductRequest.Fields.typeId)) update.setTypeId(patchProductRequest.getTypeId());
        return update;
    }

    // -------------------------------------------------------------------------
    // Target: DTO, Source: jOOQ-Record
    // -------------------------------------------------------------------------

    CreateProductResponse toCreateProductResponse(ProductRecord productRecord);

    UpdateProductResponse toUpdateProductResponse(ProductRecord productRecord);

    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "langs", ignore = true)
    ProductResponse toProductResponse(ProductRecord rec);


    @Mapping(target = "lang", ignore = true)
    ProductLangResponse toProductLangResponse(ProductLangRecord rec);

    LangResponse toLangResponse(LangRecord rec);

    RoleResponse toRoleResponse(RoleRecord rec);

    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(UserRecord rec);

    // -------------------------------------------------------------------------
    // Jooq-Specific Converters
    // -------------------------------------------------------------------------

    default ProductResponse toProductResponse(Record3<ProductRecord, UserResponse, List<ProductLangResponse>> rec) {
        return toProductResponse(rec.value1())
            .setCreator(rec.value2())
            .setLangs(rec.value3());
    }

    default ProductLangResponse toProductLangResponse(Record2<ProductLangRecord, LangResponse> rec) {
        return toProductLangResponse(rec.value1())
            .setLang(rec.value2());
    }

    default CreateProductResponse toCreateProductResponse(Record1<ProductRecord> rec) {
        return toCreateProductResponse(rec.value1());
    }

    default LangResponse toLangResponse(Record1<LangRecord> rec) {
        return toLangResponse(rec.value1());
    }

    default RoleResponse toRoleResponse(Record1<RoleRecord> rec) {
        return toRoleResponse(rec.value1());
    }

    default UserResponse toUserResponseOrNull(Record2<UserRecord, List<RoleResponse>> rec) {
        UserRecord user = rec.value1();
        if (user.getUserId() == null) {
            return null;
        } else {
            return toUserResponse(rec.value1())
                .setRoles(rec.value2());
        }
    }
}
