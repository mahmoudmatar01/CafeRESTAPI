package com.fiorecafe.fiore.fiore.models;

import lombok.Builder;

@Builder
public record ApiCustomResponse<T>(String message, int statusCode, Boolean isSuccess, T data) {
}
