package com.fiorecafe.fiore.fiore.dto.response;

import lombok.Builder;

@Builder
public record AdvResponseDto(
        Long advId,
        String advDescription,
        String advImageUrl
) {
}
