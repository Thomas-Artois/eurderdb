package com.switchfully.eurderdb.itemgroup.domain;

import com.switchfully.eurderdb.eurder.domain.Eurder;
import com.switchfully.eurderdb.item.domain.Item;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class ItemGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_group_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eurder_id")
    private Eurder eurder;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column
    private int amount;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "shipping_date")
    private Timestamp shippingDate;

    public ItemGroup() {
    }

    public ItemGroup(Eurder eurder, Item item, int amount, double totalPrice, Timestamp shippingDate) {
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
