package com.fiorecafe.fiore.fiore.controller;
import com.fiorecafe.fiore.fiore.dto.request.ItemRequestDto;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.ItemService;
import com.fiorecafe.fiore.fiore.service.impl.ItemImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/items")
@RequiredArgsConstructor
public class ItemController {
    private final ResponseFactory200 successFactory;
    private final ItemService itemService;
    private final ItemImageServiceImpl itemImageService;

    @GetMapping
    public ResponseEntity<?> getAllItem() {
        var items = itemService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @GetMapping("/best")
    public ResponseEntity<?> getBest() {
        var items = itemService.findBestItem();
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable Long itemId) {
        var items = itemService.findItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delItem(@PathVariable Long id) {
        try {
            var category = itemService.removeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(category));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBestValue(@PathVariable Long id) {
        try {
            var category = itemService.updateBestValue(id);
            return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(category));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse(ex.getMessage()));
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getItemByCategoryId(@PathVariable Long categoryId) {
        var items = itemService.findItemByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveItem(@Valid @ModelAttribute ItemRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse("Invalid request data"));
        }
        var response = itemService.saveItem(dto);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getItemImage(@PathVariable String title) {
        byte[] imageData = itemImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
