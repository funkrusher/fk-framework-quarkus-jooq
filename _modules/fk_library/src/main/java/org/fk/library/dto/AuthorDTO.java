package org.fk.library.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record AuthorDTO(
    @NotNull Integer author_id,
    @NotNull String name,
    @NotNull String nationality,
    @NotNull LocalDate birth_date,
    @NotNull String biography,
    @NotNull List<BookDTO> books
    ) {
}