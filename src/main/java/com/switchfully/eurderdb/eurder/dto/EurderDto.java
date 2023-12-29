package com.switchfully.eurderdb.eurder.dto;

import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import jakarta.persistence.*;

import java.util.List;

public class EurderDto {
    private Long id;
    private Customer customer;
    private Long customerId;
    private List<ItemGroup> itemGroups;
    private double totalPrice;

    public EurderDto() {
    }

    public EurderDto(Long id, Customer customer, List<ItemGroup> itemGroups, double totalPrice) {
        this.id = id;
        this.customer = customer;
        this.itemGroups = itemGroups;
        this.totalPrice = totalPrice;
    }

    public EurderDto(Long id, Long customerId, double totalPrice) {
        this.id = id;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
