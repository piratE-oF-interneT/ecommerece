package com.app.ecommerce.order_service.services;


import com.app.ecommerce.order_service.clients.InventoryServiceClient;
import com.app.ecommerce.order_service.dto.OrderRequestDto;
import com.app.ecommerce.order_service.entity.ItemOrdered;
import com.app.ecommerce.order_service.entity.Orders;
import com.app.ecommerce.order_service.enums.OrderStatus;
import com.app.ecommerce.order_service.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    public List<OrderRequestDto> getAllOrders() {

        List<Orders> orders = orderRepository.findAll();

        System.out.println(orders);

        return orders.stream()
                .map(order -> modelMapper.map(order, OrderRequestDto.class))
                .collect(Collectors.toList());
    }

    public OrderRequestDto getById(Long id) {

        Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("cannot find order with provided id"));

        return modelMapper.map(order, OrderRequestDto.class);
    }


//    @Retry(name = "inventoryRetry" , fallbackMethod = "createOrderFallback")
//    @RateLimiter(name = "inventoryRateLimiter" , fallbackMethod = "createOrderFallback")
    @CircuitBreaker(name = "inventoryCircuitBreaker" , fallbackMethod = "createOrderFallback")
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
        log.info("calling create-order method");

        Double totalPrice = inventoryServiceClient.reduceStock(orderRequestDto);


        Orders orders = modelMapper.map(orderRequestDto , Orders.class);

        for(ItemOrdered itemOrdered : orders.getItems()){
            itemOrdered.setOrder(orders);
        }

        orders.setPrice(totalPrice);
        orders.setOrderStatus(OrderStatus.CONFIRMED);

        Orders savedOrder = orderRepository.save(orders);

        return modelMapper.map(savedOrder , OrderRequestDto.class);

    }

    public OrderRequestDto createOrderFallback(OrderRequestDto orderRequestDto , Throwable throwable){

        log.error("fallback occured due to {}" , throwable.getMessage());

        return new OrderRequestDto();
    }

}

