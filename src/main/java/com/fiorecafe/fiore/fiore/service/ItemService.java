package com.fiorecafe.fiore.fiore.service;

import com.fiorecafe.fiore.fiore.dto.request.ItemRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.ItemResponseDto;

import java.util.List;

public interface ItemService {

    List<ItemResponseDto> findAll();
    List<ItemResponseDto> findItemByCategoryId(Long categoryId);
    ItemResponseDto saveItem(ItemRequestDto itemRequestDto);
    ItemResponseDto findItemById(Long id);
    ItemResponseDto removeById(Long id);
}
