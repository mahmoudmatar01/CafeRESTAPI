package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.dto.request.AdvRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.ItemRequestDto;
import com.fiorecafe.fiore.fiore.dto.request.UpdateAdvRequestDto;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.AdvService;
import com.fiorecafe.fiore.fiore.service.impl.AdvImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${api.version}/adv")
@RequiredArgsConstructor
@RestController
public class AdvController {

    private final AdvService advService;
    private final AdvImageServiceImpl advImageService;
    private final ResponseFactory200 successFactory;

    @GetMapping
    public ResponseEntity<?> getAllAdv() {
        var items = advService.findAll();
        return ResponseEntity.status(HttpStatus.OK).
                body(
                        successFactory
                        .createResponse(items)
                );
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveAdv(@Valid @ModelAttribute AdvRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse("Invalid request data"));
        }
        var response = advService.createAdv(dto);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
    }

    @PutMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateAdv(@Valid @ModelAttribute UpdateAdvRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse("Invalid request data"));
        }
        var response = advService.update(dto);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delAdv(@PathVariable Long id) {
        try {
            var adv = advService.delAdv(id);
            return ResponseEntity.ok(successFactory.createResponse(adv));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse(ex.getMessage()));
        }
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getItemImage(@PathVariable String title) {
        byte[] imageData = advImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
