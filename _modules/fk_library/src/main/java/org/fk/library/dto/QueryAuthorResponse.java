package org.fk.library.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QueryAuthorResponse(
    @NotNull List<AuthorDTO> authors,
    @NotNull Integer count
) {
}
