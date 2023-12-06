package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.CartResponseDto;
import com.fiorecafe.fiore.fiore.entity.cart.Cart;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CartToCartResponseDto implements Function<Cart, CartResponseDto> {
    @Override
    public CartResponseDto apply(Cart cart) {
        return CartResponseDto.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUser().getUserId())
                .userName(cart.getUser().getName())
                .cartItems(cart.getCartItems())
                .build();
    }
}
