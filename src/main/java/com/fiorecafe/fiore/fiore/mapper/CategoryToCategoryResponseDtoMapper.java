package com.fiorecafe.fiore.fiore.mapper;

import com.fiorecafe.fiore.fiore.dto.response.CategoryResponseDto;
import com.fiorecafe.fiore.fiore.entity.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CategoryToCategoryResponseDtoMapper implements Function<Category, CategoryResponseDto> {

    private final ItemToItemResponseDto itemMapper;
    @Override
    public CategoryResponseDto apply(Category category) {
        return CategoryResponseDto
                .builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryImageUrl(category.getPhotoUrl())
                .items(category.getItems().stream().map(itemMapper::apply).toList())
                .build();
    }
}
