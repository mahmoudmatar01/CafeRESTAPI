package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.config.JwtService;
import com.fiorecafe.fiore.fiore.dto.request.LoginUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.LoginUserResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.RegisterUserResponseDto;
import com.fiorecafe.fiore.fiore.entity.user.User;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundAuthenticatedUserException;
import com.fiorecafe.fiore.fiore.mapper.UserRegisterRequestDtoToUser;
import com.fiorecafe.fiore.fiore.mapper.UserRegisterToResponseDto;
import com.fiorecafe.fiore.fiore.repository.user.UserRepository;
import com.fiorecafe.fiore.fiore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final UserRegisterRequestDtoToUser userRegisterRequestDtoToUser;
    private final UserRegisterToResponseDto userRegisterToResponseDto;
    private final AuthenticationManager authenticationManager;
    @Override
    public RegisterUserResponseDto register(RegisterUserRequestDto dto) throws SQLIntegrityConstraintViolationException {
        User user = userRegisterRequestDtoToUser.apply(dto);
        try {
            User savedUser = repository.save(user);
            return userRegisterToResponseDto.apply(savedUser);
        } catch (Exception e) {
            System.out.println("Caught Exception");
            throw new BadRequestException("There is already user with that email");
        }
    }

    @Override
    public LoginUserResponseDto login(LoginUserRequestDto dto) throws NotFoundAuthenticatedUserException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.email(),
                            dto.password()
                    )
            );
        } catch (AuthenticationException e) {
            throw new NotFoundAuthenticatedUserException("There is no authenticated user with that credentials");
        }

        var user = repository.findByEmail(dto.email())
                .orElseThrow(() -> new BadRequestException("There is no user with that email."));
        Map<String, Object> claims = Map.of("userId", user.getUserId(), "email", user.getEmail());
        var token = jwtService.generateToken(claims, user);
        return LoginUserResponseDto.builder()
                .isAuthenticated(true)
                .id(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .accessToken(token)
                .build();
    }
}
