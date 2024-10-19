package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.jooq.Record1;

@Builder
public record RoleResponse(
    @NotNull String roleId
) {

    public static RoleResponse create(Record1<String> rec) {
        return RoleResponse.builder()
            .roleId(rec.value1())
            .build();
    }
}