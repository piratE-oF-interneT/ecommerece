package com.app.ecommerce.inventory_service.controller;


import com.app.ecommerce.inventory_service.clients.OrdersFeignClient;
import com.app.ecommerce.inventory_service.dto.OrderRequestDto;
import com.app.ecommerce.inventory_service.dto.ProductDto;
import com.app.ecommerce.inventory_service.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@RequestMapping("/inventory/core")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestClient restClient;

    @Autowired
    private OrdersFeignClient ordersFeignClient;

    @GetMapping("/fetchOrder")
    public String fetchOrder(){

//        ServiceInstance serviceInstance = discoveryClient.getInstances("order-service").getFirst();
//
//        log.info(serviceInstance.getHost());
//
//
//        return restClient.get()
//                .uri(serviceInstance.getUri() +"/orders/core/helloOrders")
//                .retrieve()
//                .body(String.class);

        return ordersFeignClient.getOrder();



    }


    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        return new ResponseEntity<>(productService.getAllProducts() , HttpStatus.FOUND);
    }

    @GetMapping("/id")
    public ResponseEntity<ProductDto> getProductById(@RequestParam Long id){

        return new ResponseEntity<>(productService.getProductById(id) , HttpStatus.FOUND);
    }

    @PutMapping("/reduce-stock")
    public Double reduceStock(@RequestBody OrderRequestDto orderRequestDto){

        return  productService.redueStock(orderRequestDto);
    }

}
