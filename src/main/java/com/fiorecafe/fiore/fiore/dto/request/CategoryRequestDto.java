package com.fiorecafe.fiore.fiore.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record CategoryRequestDto(String categoryName, MultipartFile image) {}