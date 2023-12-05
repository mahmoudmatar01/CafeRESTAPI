package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.request.ItemRequestDto;
import com.fiorecafe.fiore.fiore.entity.category.Category;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import com.fiorecafe.fiore.fiore.entity.item.ItemImage;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.repository.category.CategoryRepository;
import com.fiorecafe.fiore.fiore.service.impl.CategoryImageServiceImpl;
import com.fiorecafe.fiore.fiore.service.impl.ItemImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ItemRequestDtoToItem implements Function<ItemRequestDto, Item> {

    private final ItemImageServiceImpl itemImageService;
    private final CategoryRepository categoryRepository;

    @Override
    public Item apply(ItemRequestDto itemRequestDto) {
        try {
            ItemImage image= itemImageService.uploadImage(itemRequestDto.image());
            Category category=categoryRepository.findById(itemRequestDto.categoryId())
                    .orElseThrow(()->new NotFoundResourceException("There no category with this id"));
           return Item.builder()
                   .itemName(itemRequestDto.itemName())
                   .description(itemRequestDto.description())
                   .imageUrl(image.getItemPhotoUrl())
                   .itemImage(image)
                   .components(itemRequestDto.components())
                   .price(itemRequestDto.price())
                   .bestOrNot(itemRequestDto.bestOrNot())
                   .category(category)
                   .build();
        } catch (IOException e) {
            throw new BadRequestException("something went wrong");
        }
    }
}
