package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.fk.database1.testshop.tables.records.UserRecord;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.SelectField;

import java.util.List;

import static org.fk.database1.testshop2.tables.Product.PRODUCT;
import static org.jooq.impl.DSL.*;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    @NotNull Integer userId;
    @NotNull Integer clientId;
    @NotNull String email;
    @NotNull String firstname;
    @NotNull String lastname;
    @NotNull List<RoleResponse> roles;
}