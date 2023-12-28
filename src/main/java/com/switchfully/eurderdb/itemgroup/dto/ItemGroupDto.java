package com.switchfully.eurderdb.itemgroup.dto;

public class ItemGroupDto {

    private Long itemId;
    private int amount;



    public ItemGroupDto(Long itemId, int amount) {
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
