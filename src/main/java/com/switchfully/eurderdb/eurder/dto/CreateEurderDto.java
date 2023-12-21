package com.switchfully.eurderdb.eurder.dto;

import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;

import java.util.List;

public class CreateEurderDto {

    private Customer customer;
    private List<ItemGroup> itemGroups;

    private double totalPrice;

    public CreateEurderDto() {
    }

    public CreateEurderDto(Customer customer, List<ItemGroup> itemGroups, double totalPrice) {
        this.customer = customer;
        this.itemGroups = itemGroups;
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public void setItemGroups(List<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
