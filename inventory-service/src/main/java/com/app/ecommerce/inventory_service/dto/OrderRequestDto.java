package com.app.ecommerce.inventory_service.dto;

import java.util.List;

public class OrderRequestDto {

    private List<OrderRequestItemDto> items;

    public List<OrderRequestItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderRequestItemDto> items) {
        this.items = items;
    }
}
