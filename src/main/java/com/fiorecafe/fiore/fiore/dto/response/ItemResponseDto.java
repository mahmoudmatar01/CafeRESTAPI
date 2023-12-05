package com.fiorecafe.fiore.fiore.dto.response;
import lombok.Builder;

@Builder
public record ItemResponseDto(
        Long itemId,
        String itemName,
        String description,
        String itemImageUrl,
        String components,
        double price,
        boolean bestOrNot,
        Long categoryId
) {

}
