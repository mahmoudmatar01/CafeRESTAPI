package com.fiorecafe.fiore.fiore.dto.response;


import com.fiorecafe.fiore.fiore.entity.cart.CartItem;
import com.fiorecafe.fiore.fiore.entity.user.User;
import lombok.Builder;

import java.util.List;

@Builder
public record CartResponseDto(
        Long cartId,
        Long userId,
        String userName,
        List<CartItem>cartItems
) {
}
