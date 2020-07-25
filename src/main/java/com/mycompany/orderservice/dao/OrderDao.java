package com.mycompany.orderservice.dao;

import com.mycompany.orderservice.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao  extends JpaRepository<OrderModel, Long> {

    OrderModel findByCustomerName(String customerName);
}
