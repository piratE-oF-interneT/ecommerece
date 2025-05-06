package com.app.ecommerce.order_service.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrderedRepository extends JpaRepository<ItemOrdered , Long> {

}
