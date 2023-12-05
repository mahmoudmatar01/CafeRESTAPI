package com.fiorecafe.fiore.fiore.dto.request;

import lombok.Builder;

@Builder
public record UpdateAdvRequestDto(
        Long id,
        String newDescription
) {
}
