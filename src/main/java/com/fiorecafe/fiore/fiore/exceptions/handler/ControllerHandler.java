package com.fiorecafe.fiore.fiore.exceptions.handler;


import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.exceptions.MismatchPasswordException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundAuthenticatedUserException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory400;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory401;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory404;
import com.fiorecafe.fiore.fiore.models.ApiCustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseBody
@RequiredArgsConstructor
public class ControllerHandler {

    private final ResponseFactory400 badRequestFactory;
    private final ResponseFactory404 notFoundFactory;
    private final ResponseFactory401 unAuthorizedFactory;



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception) {
        return ResponseEntity
                .badRequest()
                .body(badRequestFactory.createResponse(exception.getMessage()));
    }


    @ExceptionHandler(MismatchPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiCustomResponse<?>> handleMismatchPasswordException(
            MismatchPasswordException e
    ) {
        var response = badRequestFactory.createResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundAuthenticatedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiCustomResponse<?>> handleNotFoundAuthenticatedUserException(
            NotFoundAuthenticatedUserException e
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                .body(unAuthorizedFactory.createResponse(e.getMessage()));
    }

    @ExceptionHandler(NotFoundResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiCustomResponse<?>> handleNotFoundResourceException(
            NotFoundResourceException e
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(notFoundFactory.createResponse(e.getMessage()));
    }
}
