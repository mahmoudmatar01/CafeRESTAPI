package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.RegisterUserResponseDto;
import com.fiorecafe.fiore.fiore.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRegisterToResponseDto implements Function<User, RegisterUserResponseDto> {
    @Override
    public RegisterUserResponseDto apply(User user) {
        return RegisterUserResponseDto.builder()
                .id(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
