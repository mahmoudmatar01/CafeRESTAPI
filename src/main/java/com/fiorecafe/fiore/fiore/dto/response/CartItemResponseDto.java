package com.fiorecafe.fiore.fiore.dto.response;

import com.fiorecafe.fiore.fiore.entity.cart.Cart;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import lombok.Builder;

@Builder
public record CartItemResponseDto(
        Long cartItemId,
        Item item,
        int quantity
) {
}
