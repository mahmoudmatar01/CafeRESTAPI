package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.dto.request.CategoryRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.CategoryResponseDto;
import com.fiorecafe.fiore.fiore.entity.category.Category;
import com.fiorecafe.fiore.fiore.exceptions.BadRequestException;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.mapper.CategoryRequestToCategory;
import com.fiorecafe.fiore.fiore.mapper.CategoryToCategoryResponseDtoMapper;
import com.fiorecafe.fiore.fiore.repository.category.CategoryRepository;
import com.fiorecafe.fiore.fiore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryRequestToCategory categoryRequestToCategory;
    private final CategoryToCategoryResponseDtoMapper categoryToCategoryResponseDtoMapper;

    @Override
    public List<CategoryResponseDto> findAllCategory() {
        List<Category> categoryList=categoryRepository.findAll();
        return categoryList.stream().map(categoryToCategoryResponseDtoMapper).toList();
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
            Category category=categoryRequestToCategory.apply(categoryRequestDto);
            categoryRepository.save(category);
            return categoryToCategoryResponseDtoMapper.apply(category);
    }

    @Override
    public CategoryResponseDto findCategoryById(Long id) {
        Category category=categoryRepository.findById(id)
                .orElseThrow(()->new NotFoundResourceException("There is no category with the same id "));
        var response =categoryToCategoryResponseDtoMapper.apply(category);
        return response;
    }

    @Override
    public CategoryResponseDto removeCategory(Long id) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundResourceException("There is no category with the same id "));

            categoryRepository.deleteById(id);
            return categoryToCategoryResponseDtoMapper.apply(category);
        } catch (DataIntegrityViolationException | EmptyResultDataAccessException ex) {
            throw new BadRequestException("Unable to delete category with id " + id);
        }
    }
}
