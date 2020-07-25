package com.mycompany.orderservice.dto;

import com.mycompany.orderservice.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class Order {
    private Long orderId;
    private String customerName;
    private String orderDate;
    private String shippingAddress;
    private List<OrderItem> orderItems;
    private Long total;
}
