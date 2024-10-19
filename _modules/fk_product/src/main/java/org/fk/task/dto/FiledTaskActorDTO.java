package org.fk.task.dto;

import lombok.Builder;

@Builder
public record FiledTaskActorDTO(
    Integer clientId,
    String name
) {
}
