package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.exceptions.ItemDoesntExistException;
import com.switchfully.eurderdb.item.dto.CreateItemDto;
import com.switchfully.eurderdb.item.dto.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto saveItem(CreateItemDto createItemDto) {
        return itemMapper.mapItemToItemDto(
                itemRepository.save(
                        itemMapper.mapCreateItemDtoToItem(
                                createItemDto)));
    }

    public ItemDto findItemById(Long itemId) throws ItemDoesntExistException {
        return itemMapper.mapItemToItemDto(itemRepository.findById(itemId).orElseThrow(ItemDoesntExistException::new));
    }

    public List<ItemDto> findAllItems() {
        return itemRepository.findAll().stream().map(itemMapper::mapItemToItemDto).collect(Collectors.toList());
    }
}
