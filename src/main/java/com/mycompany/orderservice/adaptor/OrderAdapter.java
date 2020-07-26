package com.mycompany.orderservice.adaptor;

import com.mycompany.orderservice.dto.Order;
import com.mycompany.orderservice.model.OrderModel;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderAdapter {

    public Order fromModel(OrderModel orderModel) {
        Order order = null;
        if (orderModel != null) {
            order = new Order();
            order.setCustomerName(orderModel.getCustomerName());
            order.setOrderDate(toStringDate(orderModel.getOrderDate()));
            order.setShippingAddress(orderModel.getShippingAddress());
            order.setTotal(orderModel.getTotal());
            order.setOrderId(orderModel.getOrderId());
        }
        return order;
    }

    private String toStringDate(Date orderDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(orderDate);
    }

    public OrderModel fromDto(Order order) {
        OrderModel orderModel = null;
        if (order != null) {
            orderModel = new OrderModel();
            orderModel.setOrderId(order.getOrderId());
            orderModel.setCustomerName(order.getCustomerName());
            orderModel.setShippingAddress(order.getShippingAddress());
            orderModel.setTotal(order.getTotal());
        }
        return orderModel;
    }
}
