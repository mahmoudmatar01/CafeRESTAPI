package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.LoginUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.LoginUserResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.RegisterUserResponseDto;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundAuthenticatedUserException;

import java.sql.SQLIntegrityConstraintViolationException;

public interface AuthService {
     RegisterUserResponseDto register(RegisterUserRequestDto dto) throws SQLIntegrityConstraintViolationException;
     LoginUserResponseDto login(LoginUserRequestDto dto) throws NotFoundAuthenticatedUserException;
}
