package com.app.ecommerce.order_service.clients;

import com.app.ecommerce.order_service.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service" , path = "/inventory")
public interface InventoryServiceClient {

    @PutMapping("/core/reduce-stock")
    Double reduceStock(@RequestBody OrderRequestDto orderRequestDto);
}
