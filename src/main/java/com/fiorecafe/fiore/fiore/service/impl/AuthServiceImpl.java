package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.dto.request.LoginUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.LoginUserResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.RegisterUserResponseDto;
import com.fiorecafe.fiore.fiore.entity.user.User;
import com.fiorecafe.fiore.fiore.enums.Role;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundAuthenticatedUserException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.user.UserRepository;
import com.fiorecafe.fiore.fiore.service.AuthService;
import com.fiorecafe.fiore.fiore.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.EOFException;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Override
    public RegisterUserResponseDto register(RegisterUserRequestDto requestDto) throws IOException {
        var user = User
                .builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .role(Role.Role_User)
                .password(passwordEncoder.encode(requestDto.password()))
                .gender(requestDto.gender())
                .build();

        try {
            User savedUser = repository.save(user);
            return RegisterUserResponseDto
                    .builder()
                    .email(savedUser.getEmail())
                    .name(savedUser.getUsername())
                    .role(savedUser.getRole())
                    .build();
        } catch (Exception e) {
            System.out.println("Caught Exception");
            throw new EOFException("There is already user with that user name");
        }
    }

    @Override
    public LoginUserResponseDto login(LoginUserRequestDto loginDto) throws NotFoundAuthenticatedUserException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.email(),
                            loginDto.password()
                    )
            );
        } catch (AuthenticationException e) {
            throw new NotFoundAuthenticatedUserException("There is no authenticated user with that credentials");
        }

        var user = repository.findByEmail(loginDto.email())
                .orElseThrow(() -> new NotFoundResourceException("There is no user with that email."));
        Map<String, Object> claims = Map.of("userId", user.getUserId(), "email", user.getEmail());
        var token = jwtService.generateToken(claims, user);
        return LoginUserResponseDto.builder()
                .isAuthenticated(true)
                .name(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .accessToken(token)
                .build();
    }
}
