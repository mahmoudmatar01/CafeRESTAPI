package com.fiorecafe.fiore.fiore.controller;
import com.fiorecafe.fiore.fiore.dto.request.BestSellingItemRequestDto;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.BestSellingItemsService;
import com.fiorecafe.fiore.fiore.service.impl.BestSellingImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/best-items")
@RequiredArgsConstructor
public class BestSellingController {
    private final ResponseFactory200 successFactory;
    private final BestSellingItemsService itemService;
    private final BestSellingImageServiceImpl itemImageService;

    @GetMapping
    public ResponseEntity<?> getAllItem() {
        var items = itemService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable Long itemId) {
        var items = itemService.getById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(items));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveItem(@Valid @ModelAttribute BestSellingItemRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse("Invalid request data"));
        }
        var response = itemService.createItem(dto);
        return ResponseEntity.status(HttpStatus.OK).body(successFactory.createResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delItem(@PathVariable Long id) {
        try {
            var category = itemService.delById(id);
            return ResponseEntity.ok(successFactory.createResponse(category));
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(successFactory.createResponse(ex.getMessage()));
        }
    }
    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getItemImage(@PathVariable String title) {
        byte[] imageData = itemImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}

