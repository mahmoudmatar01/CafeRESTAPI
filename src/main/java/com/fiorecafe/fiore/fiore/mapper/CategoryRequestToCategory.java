package com.fiorecafe.fiore.fiore.mapper;
import com.fiorecafe.fiore.fiore.dto.request.CategoryRequestDto;
import com.fiorecafe.fiore.fiore.entity.category.Category;
import com.fiorecafe.fiore.fiore.entity.category.CategoryImage;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.service.impl.CategoryImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CategoryRequestToCategory implements Function<CategoryRequestDto, Category> {

    private final CategoryImageServiceImpl categoryImageService;
    @Override
    public Category apply(CategoryRequestDto categoryRequestDto) {
        try {
            CategoryImage categoryImage=categoryImageService.uploadImage(categoryRequestDto.image());
            return Category
                    .builder()
                    .categoryName(categoryRequestDto.categoryName())
                    .categoryImage(categoryImage)
                    .photoUrl(categoryImage.getCategoryImageUrl())
                    .items(new ArrayList<>())
                    .build();
        } catch (IOException e) {
            throw new BadRequestException("something went wrong");
        }
    }
}
