package com.fiorecafe.fiore.fiore.factories.impl;

import com.fiorecafe.fiore.fiore.factories.ResponseFactory;
import com.fiorecafe.fiore.fiore.models.ApiCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory404 implements ResponseFactory<Object> {
    @Override
    public ApiCustomResponse<?> createResponse(String message) {
        return ApiCustomResponse.builder()
                .message(message)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .isSuccess(false)
                .build();
    }

    @Override
    public ApiCustomResponse<Object> createResponse(Object data) {
        return ApiCustomResponse.builder()
                .data(data)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .isSuccess(false)
                .build();
    }
}
