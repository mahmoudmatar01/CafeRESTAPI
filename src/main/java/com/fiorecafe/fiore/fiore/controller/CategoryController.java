package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.dto.request.CategoryRequestDto;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.CategoryService;
import com.fiorecafe.fiore.fiore.service.impl.CategoryImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.version}/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ResponseFactory200 successFactory;
    private final CategoryService categoryService;
    private final CategoryImageServiceImpl categoryImageService;

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        var categories = categoryService.findAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(categories));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var categories = categoryService.findCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(categories));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delCategory(@PathVariable Long id) {
        try {
            var category = categoryService.removeCategory(id);
            return ResponseEntity.ok(successFactory.createResponse(category));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse(ex.getMessage()));
        }
    }
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveCategory(@Valid @ModelAttribute CategoryRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse("Invalid request data"));
        }
        var response = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getCategoryImage(@PathVariable String title) {
        byte[] imageData = categoryImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
