package com.app.ecommerce.order_service.controller;

import com.app.ecommerce.order_service.configs.AppUpdateConfig;
import com.app.ecommerce.order_service.dto.OrderRequestDto;
import com.app.ecommerce.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/core")
@RefreshScope
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AppUpdateConfig appUpdateConfig;

    @GetMapping("/helloOrders")
    public String getHelloFromOrders(@RequestHeader(name = "X-UserId") String userId){

        if (appUpdateConfig.isDownTime()){
            return "this is app downtime cannot proceed requests for some time";
        }

        return "hello from order service with user id "+ userId;
    }

    @GetMapping("/welcomeOrder")
    public String welcomeOrder(){

        if (appUpdateConfig.isDownTime()){
            return "this is app downtime cannot proceed requests for some time";
        }

        return "welcome from order service";
    }

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders() {
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        OrderRequestDto order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/create-order")

    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){


        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);

        return new ResponseEntity<>(orderRequestDto1 , HttpStatus.CREATED);

    }
}
