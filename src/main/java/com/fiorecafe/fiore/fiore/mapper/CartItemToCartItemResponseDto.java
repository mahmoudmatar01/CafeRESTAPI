package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.CartItemResponseDto;
import com.fiorecafe.fiore.fiore.entity.cart.CartItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CartItemToCartItemResponseDto implements Function<CartItem, CartItemResponseDto> {
    @Override
    public CartItemResponseDto apply(CartItem cartItem) {
        return CartItemResponseDto.builder()
                .cartItemId(cartItem.getCartItemId())
                .quantity(cartItem.getQuantity())
                .item(cartItem.getItem())
                .build();
    }
}
