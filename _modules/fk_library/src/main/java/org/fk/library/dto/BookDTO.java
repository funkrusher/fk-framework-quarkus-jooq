package org.fk.library.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookDTO(
    @NotNull Integer book_id,
    @NotNull String title,
    @NotNull Integer author_id,
    @NotNull String genre,
    @NotNull LocalDate publication_date,
    @NotNull String isbn
) {
}