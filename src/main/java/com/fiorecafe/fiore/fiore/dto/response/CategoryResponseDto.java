package com.fiorecafe.fiore.fiore.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude
public record CategoryResponseDto(
        Long categoryId,
        String categoryName,
        String categoryImageUrl,
        List<ItemResponseDto> items
) {
}
