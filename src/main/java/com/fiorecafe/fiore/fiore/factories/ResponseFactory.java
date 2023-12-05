package com.fiorecafe.fiore.fiore.factories;


import com.fiorecafe.fiore.fiore.models.ApiCustomResponse;

public interface ResponseFactory<T> {
    ApiCustomResponse<?> createResponse(String message);
    ApiCustomResponse<T> createResponse(T data);
}
