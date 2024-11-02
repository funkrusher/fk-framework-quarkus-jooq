package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.jooq.Record1;
import org.jooq.Field;

import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.select;

@Builder
public record RoleResponse(
    @NotNull String roleId
) {

    public static RoleResponse create(Record1<String> rec) {
        return RoleResponse.builder()
            .roleId(rec.value1())
            .build();
    }

    public static Field<List<RoleResponse>> rolesSelector() {
        return multiset(
            select(
                PRODUCT.user().userRole().role().ROLEID
            ).from(PRODUCT.user().userRole().role())
        ).convertFrom(r -> r.map(RoleResponse::create));
    }
}