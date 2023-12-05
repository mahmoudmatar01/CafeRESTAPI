package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.CategoryRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> findAllCategory();
    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto findCategoryById(Long id);
    CategoryResponseDto removeCategory(Long id);

}
