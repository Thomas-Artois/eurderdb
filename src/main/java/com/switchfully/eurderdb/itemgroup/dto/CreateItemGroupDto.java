package com.switchfully.eurderdb.itemgroup.dto;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.item.domain.Item;

import java.sql.Timestamp;

public class CreateItemGroupDto {

    private Long itemId;
    private int amount;

    public CreateItemGroupDto(Long itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
