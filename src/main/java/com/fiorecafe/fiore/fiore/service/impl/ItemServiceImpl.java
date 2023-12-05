package com.fiorecafe.fiore.fiore.service.impl;
import com.fiorecafe.fiore.fiore.dto.request.ItemRequestDto;
import com.fiorecafe.fiore.fiore.dto.response.ItemResponseDto;
import com.fiorecafe.fiore.fiore.entity.item.Item;
import com.fiorecafe.fiore.fiore.exceptions.NotFoundResourceException;
import com.fiorecafe.fiore.fiore.mapper.ItemRequestDtoToItem;
import com.fiorecafe.fiore.fiore.mapper.ItemToItemResponseDto;
import com.fiorecafe.fiore.fiore.repository.item.ItemRepository;
import com.fiorecafe.fiore.fiore.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private  final ItemRepository itemRepository;
    private final ItemRequestDtoToItem itemRequestDtoToItem;
    private final ItemToItemResponseDto itemToItemResponseDto;

    @Override
    public List<ItemResponseDto> findAll() {
        List<Item>itemLists=itemRepository.findAll();
        return itemLists.stream().map(itemToItemResponseDto::apply).toList();
    }

    @Override
    public List<ItemResponseDto> findItemByCategoryId(Long categoryId) {
        List<Item>items=itemRepository.findItemsByCategoryId(categoryId);
        return items.stream().map(itemToItemResponseDto::apply).toList();
    }

    @Override
    public ItemResponseDto saveItem(ItemRequestDto itemRequestDto) {
        Item item=itemRequestDtoToItem.apply(itemRequestDto);
        itemRepository.save(item);
        return itemToItemResponseDto.apply(item);
    }

    @Override
    public ItemResponseDto findItemById(Long id) {
        Item item=itemRepository.findById(id).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this id")
        );
        return itemToItemResponseDto.apply(item);
    }

    @Override
    public ItemResponseDto removeById(Long id) {
        Item item=itemRepository.findById(id).orElseThrow(
                ()->new NotFoundResourceException("there is no item with this id")
        );
        itemRepository.delete(item);
        return itemToItemResponseDto.apply(item);
    }
}
