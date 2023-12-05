package com.fiorecafe.fiore.fiore.dto.response;

import com.fiorecafe.fiore.fiore.enums.Role;
import lombok.Builder;

@Builder
public record RegisterUserResponseDto(
        Integer id,
        String name,
        String email,
        Role role
) {
}
