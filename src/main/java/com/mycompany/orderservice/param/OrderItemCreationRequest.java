package com.mycompany.orderservice.param;

import com.mycompany.orderservice.model.OrderItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class OrderItemCreationRequest {
    private Long orderId;
    private List<OrderItem> orderItemModelList;
}
