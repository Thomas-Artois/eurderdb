package com.switchfully.eurderdb.eurder.domain;

import com.switchfully.eurderdb.customer.domain.Customer;
import com.switchfully.eurderdb.itemgroup.domain.ItemGroup;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Eurder {
    @Id
    @Column(name = "eurder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "eurder", cascade = CascadeType.ALL)
    private List<ItemGroup> itemGroups;

    @Column(name = "total_price")
    private double totalPrice;

    public Eurder() {
    }

    public Eurder(Customer customer, List<ItemGroup> itemGroups, double totalPrice) {
        this.customer = customer;
        this.itemGroups = itemGroups;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
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
