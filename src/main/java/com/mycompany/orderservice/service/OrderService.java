package com.mycompany.orderservice.service;

import com.mycompany.orderservice.dto.Order;
import com.mycompany.orderservice.param.OrderResponse;

public interface OrderService {
    void createOrder(Order order);
    OrderResponse retrieveOrder(String customerName);
}
