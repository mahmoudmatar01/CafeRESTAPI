package com.fiorecafe.fiore.fiore.dto.response;

import com.fiorecafe.fiore.fiore.enums.Role;
import lombok.Builder;

@Builder
public record LoginUserResponseDto(
        Long id,
        String name,
        String email,
        Role role,
        String firebaseToken,
        boolean isAuthenticated,
        String accessToken
) {
}
