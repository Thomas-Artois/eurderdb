package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.item.domain.Item;
import com.switchfully.eurderdb.item.dto.CreateItemDto;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ItemServiceTests {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void givenItem_whenSaveItem_thenItemIsSavedInDatabase() {
        //GIVEN
        CreateItemDto createItemDto =
                new CreateItemDto(
                        "Chair",
                        "Stores people",
                        2.82,
                        2
                );


        //WHEN
        itemService.saveItem(createItemDto);

        //THEN
        assertThat(itemRepository.findById(3L)).isPresent();
        assertThat(itemRepository.findById(3L).get().getDescription()).isEqualTo("Stores people");

    }
}
