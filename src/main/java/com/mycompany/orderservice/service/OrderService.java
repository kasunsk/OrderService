package com.mycompany.orderservice.service;

import com.mycompany.orderservice.dto.Order;

public interface OrderService {
    void createOrder(Order order);
    Order retrieveOrder(String customerName);
}
