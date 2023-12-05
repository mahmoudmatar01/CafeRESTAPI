package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.dto.request.LoginUserRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.RegisterUserRequestDto;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.AuthService;
import com.fiorecafe.fiore.fiore.service.impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RequestMapping("${api.version}/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ResponseFactory200 successFactory;
    private final UserImageServiceImpl userImageService;
    private final AuthService authService;

    @PostMapping(value = "/register",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> register(@ModelAttribute RegisterUserRequestDto dto){
        try {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(successFactory.createResponse(authService.register(dto))
                    );
        } catch (SQLIntegrityConstraintViolationException e) {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(successFactory.createResponse("something went wrong : "+e.getMessage())
                    );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequestDto dto) {
            return  ResponseEntity.status(HttpStatus.OK).
                    body(successFactory.createResponse(authService.login(dto))
                    );
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getItemImage(@PathVariable String title) {
        byte[] imageData = userImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
