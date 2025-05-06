package com.app.ecommerce.order_service.dto;


import java.util.List;

public class OrderRequestDto {

    private Long id;

    private String orderStatus;

    private Double totalPrice;

    private List<ItemOrderedRequestDto> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ItemOrderedRequestDto> getItems() {
        return items;
    }

    public void setItems(List<ItemOrderedRequestDto> items) {
        this.items = items;
    }
}
