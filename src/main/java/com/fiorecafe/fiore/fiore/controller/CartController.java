package com.fiorecafe.fiore.fiore.controller;

import com.fiorecafe.fiore.fiore.dto.request.AddToCartRequestDto;
import com.fiorecafe.fiore.fiore.factories.impl.ResponseFactory200;
import com.fiorecafe.fiore.fiore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${api.version}/cart")
@RequiredArgsConstructor
@RestController
public class CartController {
    private final ResponseFactory200 successFactory;
    private final CartService cartService;
    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        var cart = cartService.getCartByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).
                body(successFactory.createResponse(cart));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createCart(@PathVariable Long userId) {
        var cart = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.OK).
                body(successFactory.createResponse(cart));
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToCart(
           @RequestBody AddToCartRequestDto dto) {
        var cartItem = cartService.addToCart(dto);
        return ResponseEntity.status(HttpStatus.OK).
                body(successFactory.createResponse(cartItem));
    }

    @DeleteMapping("/{userId}/removeItem/{itemId}")
    public ResponseEntity<?> removeItemFromCart(
            @PathVariable Long userId,
            @PathVariable Long itemId) {
        var cartItem=cartService.removeFromCart(userId, itemId);
        return ResponseEntity.status(HttpStatus.OK).
                body(successFactory.createResponse(cartItem));
    }
}
