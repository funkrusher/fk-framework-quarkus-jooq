package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;

import java.util.List;

@Builder
public record UserDTO(
    @NotNull Integer userId,
    @NotNull Integer clientId,
    @NotNull String email,
    @NotNull String firstname,
    @NotNull String lastname,
    @NotNull List<RoleDTO> roles
) {

    public static UserDTO createOrNull(Record2<UserRecord, List<RoleDTO>> rec) {
        UserRecord user = rec.value1();
        if (user.getUserid() == null) {
            return null;
        } else {
            return UserDTO.builder()
                .userId(user.getUserid())
                .clientId(user.getClientid())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(rec.value2())
                .build();
        }
    }
}