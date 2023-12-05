package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.entity.user.User;
import com.fiorecafe.fiore.fiore.entity.user.UserImage;
import com.fiorecafe.fiore.fiore.enums.Role;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.service.impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class UserRegisterRequestDtoToUser implements Function<RegisterUserRequestDto, User> {

    private final UserImageServiceImpl userImageService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User apply(RegisterUserRequestDto registerUserRequestDto) {
        try {
           UserImage image= userImageService.uploadImage(registerUserRequestDto.image());
            return User.builder()
                    .name(registerUserRequestDto.name())
                    .email(registerUserRequestDto.email())
                    .password(passwordEncoder.encode(registerUserRequestDto.password()))
                    .role(Role.Role_User)
                    .gender(registerUserRequestDto.gender())
                    .userImage(image)
                    .userImageUrl(image.getUserPhotoUrl())
                    .build();
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
