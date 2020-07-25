package com.mycompany.orderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderItem {
    private String productCode;
    private String productName;
    private Long quantity;
}
