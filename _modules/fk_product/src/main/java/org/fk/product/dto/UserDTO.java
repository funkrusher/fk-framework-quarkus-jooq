package org.fk.product.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDTO(
    @NotNull Integer userId,
    @NotNull Integer clientId,
    @NotNull String email,
    @NotNull String firstname,
    @NotNull String lastname,
    @NotNull List<RoleDTO> roles
) {
}