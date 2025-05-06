package com.app.ecommerce.order_service.entity;


import com.app.ecommerce.order_service.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalPrice;


    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<ItemOrdered> items;

    public Orders(){

    }

    public Orders(Long id, OrderStatus orderStatus, Double price, List<ItemOrdered> items) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalPrice = price;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemOrdered> getItems() {
        return items;
    }

    public void setItems(List<ItemOrdered> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getPrice() {
        return this.totalPrice;
    }

    public void setPrice(Double price) {
        this.totalPrice = price;
    }
}
