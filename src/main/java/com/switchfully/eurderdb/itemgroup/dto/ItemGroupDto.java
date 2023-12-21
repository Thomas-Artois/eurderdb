package com.switchfully.eurderdb.itemgroup.dto;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.item.domain.Item;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

public class ItemGroupDto {
    private Long id;
    private Eurder eurder;
    private Item item;
    private int amount;
    private double totalPrice;
    private Timestamp shippingDate;

    public ItemGroupDto() {
    }

    public ItemGroupDto(Long id, Eurder eurder, Item item, int amount, double totalPrice, Timestamp shippingDate) {
        this.id = id;
        this.eurder = eurder;
        this.item = item;
        this.amount = amount;
        this.totalPrice = totalPrice;
        this.shippingDate = shippingDate;
    }

    public Long getId() {
        return id;
    }

    public Eurder getEurder() {
        return eurder;
    }

    public void setEurder(Eurder eurder) {
        this.eurder = eurder;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Timestamp shippingDate) {
        this.shippingDate = shippingDate;
    }
}
