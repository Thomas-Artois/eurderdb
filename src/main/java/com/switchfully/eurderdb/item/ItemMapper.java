package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.item.domain.Item;
import com.switchfully.eurderdb.item.dto.CreateItemDto;
import com.switchfully.eurderdb.item.dto.ItemDto;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item mapCreateItemDtoToItem(CreateItemDto createItemDto) {
        return new Item(
                createItemDto.getName(),
                createItemDto.getDescription(),
                createItemDto.getPrice(),
                createItemDto.getStockAmount()
        );
    }

    public ItemDto mapItemToItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStockAmount()
        );
    }
}
