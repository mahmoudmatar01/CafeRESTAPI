package com.fiorecafe.fiore.fiore.service.impl;

import com.fiorecafe.fiore.fiore.dto.request.BestSellingItemRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.BestSellingResponseDto;
import com.fiorecafe.fiore.fiore.entity.best_selling.BestSellingItem;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.mapper.BestSellingItemRequestDtoToItem;
import com.fiorecafe.fiore.fiore.mapper.BestSellingItemToResponseDto;
import com.fiorecafe.fiore.fiore.repository.best_selling.BestSellingItemRepository;
import com.fiorecafe.fiore.fiore.service.BestSellingItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BestSellingItemServiceImpl implements BestSellingItemsService {

    private final BestSellingItemRepository itemRepository;
    private final BestSellingItemToResponseDto toResponseDto;
    private final BestSellingItemRequestDtoToItem requestDtoToItem;

    @Override
    public BestSellingResponseDto createItem(BestSellingItemRequestDto dto) {
        BestSellingItem item=requestDtoToItem.apply(dto);
        itemRepository.save(item);
        return toResponseDto.apply(item);
    }

    @Override
    public List<BestSellingResponseDto> findAll() {
        List<BestSellingItem>items=itemRepository.findAll();
        return items.stream()
                .map(item->toResponseDto.apply(item))
                .toList();
    }

    @Override
    public BestSellingResponseDto getById(Long id) {
        BestSellingItem item=itemRepository.findById(id).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this id")
        );
        return toResponseDto.apply(item);
    }

    @Override
    public BestSellingResponseDto delById(Long id) {
        BestSellingItem item=itemRepository.findById(id).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this id")
        );
        itemRepository.delete(item);
        return toResponseDto.apply(item);    }
}
