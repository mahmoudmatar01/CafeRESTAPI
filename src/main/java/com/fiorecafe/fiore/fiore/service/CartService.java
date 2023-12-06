package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.AddToCartRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.CartItemResponseDto;
import com.fiorecafe.fiore.fiore.dto.response.CartResponseDto;
import com.fiorecafe.fiore.fiore.entity.cart.Cart;
import com.fiorecafe.fiore.fiore.entity.cart.CartItem;


public interface CartService {
    CartResponseDto createCart(Long userId);
    CartResponseDto getCartByUser(Long userId);
    CartItemResponseDto addToCart(AddToCartRequestDto dto);
    CartItemResponseDto removeFromCart(Long userId, Long itemId);
}
