package com.switchfully.eurderdb.item;

import com.switchfully.eurderdb.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ItemRepository extends JpaRepository<Item, Long> {
}
