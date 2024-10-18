package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.jooq.Record1;

@Builder
public record RoleDTO(
    @NotNull String roleId
) {

    public static RoleDTO create(Record1<String> rec) {
        return RoleDTO.builder()
            .roleId(rec.value1())
            .build();
    }
}