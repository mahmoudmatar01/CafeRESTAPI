package com.fiorecafe.fiore.fiore.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record BestSellingItemRequestDto(
        String itemName,
        MultipartFile image,
        String description,
        String components,
        double price
) {
}
