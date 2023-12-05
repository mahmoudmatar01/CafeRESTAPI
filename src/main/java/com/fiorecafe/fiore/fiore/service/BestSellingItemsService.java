package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.BestSellingItemRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.BestSellingResponseDto;

import java.util.List;

public interface BestSellingItemsService {
    BestSellingResponseDto createItem(BestSellingItemRequestDto dto);
    List<BestSellingResponseDto> findAll();
    BestSellingResponseDto getById(Long id);
    BestSellingResponseDto delById(Long id);
}
