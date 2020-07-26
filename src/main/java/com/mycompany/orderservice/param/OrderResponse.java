package com.mycompany.orderservice.param;

import com.mycompany.orderservice.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class OrderResponse {
    private String customerName;
    private String orderDate;
    private String shippingAddress;
    private List<OrderItem> orderItems;
    private Long total;
}
