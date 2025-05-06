package com.app.ecommerce.inventory_service.services;


import com.app.ecommerce.inventory_service.dto.OrderRequestDto;
import com.app.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.app.ecommerce.inventory_service.dto.ProductDto;
import com.app.ecommerce.inventory_service.entity.Product;
import com.app.ecommerce.inventory_service.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDto> getAllProducts(){

        List<Product> products = productRepository.findAll();
        if (products == null){
            throw new RuntimeException("db is empty");
        }
        return products.stream()
                .map(product -> modelMapper.map(product , ProductDto.class))
                .collect(Collectors.toList());


    }

    public ProductDto getProductById(Long id){

        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found with this id"));

        return modelMapper.map(product , ProductDto.class);
    }

    @Transactional
    public Double redueStock(OrderRequestDto orderRequestDto) throws RuntimeException {

        double totalPrice = 0.0;

        for(OrderRequestItemDto orderRequestItemDto : orderRequestDto.getItems()){

            Product product = productRepository.findById(orderRequestItemDto.getProductId()).orElseThrow(() -> new RuntimeException("invalid order id."));

            if (product.getStock() < orderRequestItemDto.getQuantity()){
                throw new RuntimeException("product"+ product.getTitle() + "is out of stock");
            }
            product.setStock(product.getStock() - orderRequestItemDto.getQuantity());

            totalPrice += orderRequestItemDto.getQuantity()*product.getPrice();


        }

        return totalPrice;
    }
}
