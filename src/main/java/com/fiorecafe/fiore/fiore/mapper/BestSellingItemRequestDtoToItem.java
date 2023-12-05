package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.request.BestSellingItemRequestDto;
import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItem;
import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItemImage;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.service.impl.BestSellingImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BestSellingItemRequestDtoToItem implements Function<BestSellingItemRequestDto, BestSellingItem> {

    private final BestSellingImageServiceImpl imageService;
    @Override
    public BestSellingItem apply(BestSellingItemRequestDto bestSellingItemRequestDto) {
        try {
            BestSellingItemImage image=imageService.uploadImage(bestSellingItemRequestDto.image());
            return BestSellingItem.builder()
                    .itemName(bestSellingItemRequestDto.itemName())
                    .description(bestSellingItemRequestDto.description())
                    .components(bestSellingItemRequestDto.components())
                    .itemImage(image)
                    .imageUrl(image.getPhotoUrl())
                    .price(bestSellingItemRequestDto.price())
                    .build();

        } catch (IOException e) {
            throw new BadRequestException("something went wrong because : "+e.getMessage());
        }
    }
}
