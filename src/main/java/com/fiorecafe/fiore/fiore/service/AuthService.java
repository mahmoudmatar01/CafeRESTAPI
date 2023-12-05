package com.fiorecafe.fiore.fiore.service;


import com.fiorecafe.fiore.fiore.dto.request.LoginUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.LoginUserResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.RegisterUserResponseDto;

import java.io.IOException;

public interface AuthService {
    RegisterUserResponseDto register(RegisterUserRequestDto requestDto) throws IOException;

    LoginUserResponseDto login(LoginUserRequestDto loginDto);

}
