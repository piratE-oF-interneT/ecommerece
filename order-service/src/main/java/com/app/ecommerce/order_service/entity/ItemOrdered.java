package com.app.ecommerce.order_service.entity;


import com.app.ecommerce.order_service.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.query.Order;

@Entity
public class ItemOrdered {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long productId;

    private Integer quantity;

    private Double price;



    @ManyToOne(optional = false,cascade = CascadeType.ALL )
    private Orders order;

    public ItemOrdered(){

    }

    public ItemOrdered(Long id, Long productId, Integer quantity, Double price, Orders order) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
