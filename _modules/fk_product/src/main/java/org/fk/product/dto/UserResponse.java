package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.SelectField;

import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.*;

@Builder
public record UserResponse(
    @NotNull Integer userId,
    @NotNull Integer clientId,
    @NotNull String email,
    @NotNull String firstname,
    @NotNull String lastname,
    @NotNull List<RoleResponse> roles
) {

    public static UserResponse createOrNull(Record2<UserRecord, List<RoleResponse>> rec) {
        UserRecord user = rec.value1();
        if (user.getUserId() == null) {
            return null;
        } else {
            return UserResponse.builder()
                .userId(user.getUserId())
                .clientId(user.getClientId())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(rec.value2())
                .build();
        }
    }

    public static SelectField<UserResponse> userSelector() {
        return row(
            PRODUCT.user(),
            RoleResponse.rolesSelector()
        ).convertFrom(UserResponse::createOrNull);
    }
}