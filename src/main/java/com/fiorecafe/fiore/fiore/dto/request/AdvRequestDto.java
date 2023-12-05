package com.fiorecafe.fiore.fiore.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record AdvRequestDto(
        String advDescription,
        MultipartFile image
) {
}
