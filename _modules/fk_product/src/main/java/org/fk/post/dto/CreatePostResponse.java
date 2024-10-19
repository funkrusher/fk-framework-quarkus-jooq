package org.fk.post.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreatePostResponse(
    @NotNull UUID id,
    @NotNull String title
) {
}