package com.fiorecafe.fiore.fiore.dto.request;

import com.fiorecafe.fiore.fiore.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginUserRequestDto(
        @NotBlank(message = "email is required")
        @NotEmpty(message = "email is required")
        @NotNull(message = "email is required")
        String email,

        @NotBlank(message = "password is required")
        @NotEmpty(message = "password is required")
        @NotNull(message = "password is required")
        String password
        ) {
}
