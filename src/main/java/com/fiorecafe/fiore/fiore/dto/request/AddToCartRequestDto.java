package com.fiorecafe.fiore.fiore.dto.request;

import lombok.Builder;

@Builder
public record AddToCartRequestDto(
        Long userId,
        Long itemId,
        int quantity
) {
}
