package com.fiorecafe.fiore.fiore.dto.request;

import com.fiorecafe.fiore.fiore.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record RegisterUserRequestDto(
        @NotNull(message = "Name is required")
        @NotBlank(message = "Name is required")
        String name,
        @NotNull(message = "email is required")
        @NotBlank(message = "email is required")
        String email,
        Gender gender,
        @NotNull(message = "password is required")
        @NotBlank(message = "password is required")
        String password
) {


}
