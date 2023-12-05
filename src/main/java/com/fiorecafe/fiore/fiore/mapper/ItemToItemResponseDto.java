package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.ItemResponseDto;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ItemToItemResponseDto implements Function<Item, ItemResponseDto> {

    @Override
    public ItemResponseDto apply(Item item) {
        return ItemResponseDto
                .builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .description(item.getDescription())
                .itemImageUrl(item.getImageUrl())
                .price(item.getPrice())
                .components(item.getComponents())
                .bestOrNot(item.isBestOrNot())
                .categoryId(item.getCategory().getCategoryId())
                .build();
    }
}
