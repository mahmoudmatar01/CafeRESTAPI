package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/greeting")
@RequiredArgsConstructor
public class GreetingController {

    private final GreetingService greetingService;
    private final ResponseFactory200 successFactory;

    @GetMapping
    public ResponseEntity<?> getAboutText() {
        Object aboutText = greetingService.getGreeting();
        return ResponseEntity.ok(successFactory.createResponse(aboutText));
    }

}
