package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.BestSellingResponseDto;
import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BestSellingItemToResponseDto implements Function<BestSellingItem, BestSellingResponseDto> {
    @Override
    public BestSellingResponseDto apply(BestSellingItem bestSellingItem) {
        return BestSellingResponseDto
                .builder()
                .itemId(bestSellingItem.getId())
                .itemName(bestSellingItem.getItemName())
                .description(bestSellingItem.getDescription())
                .itemImageUrl(bestSellingItem.getImageUrl())
                .components(bestSellingItem.getComponents())
                .price(bestSellingItem.getPrice())
                .build();
    }
}
