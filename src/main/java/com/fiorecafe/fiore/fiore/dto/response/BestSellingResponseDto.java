package com.fiorecafe.fiore.fiore.dto.response;

import lombok.Builder;

@Builder
public record BestSellingResponseDto(
        Long itemId,
        String itemName,
        String description,
        String itemImageUrl,
        String components,
        double price
) {
}
