package com.mycompany.orderservice.service.impl;

import com.mycompany.orderservice.adaptor.OrderAdapter;
import com.mycompany.orderservice.dao.OrderDao;
import com.mycompany.orderservice.dto.Order;
import com.mycompany.orderservice.model.OrderItem;
import com.mycompany.orderservice.model.OrderModel;
import com.mycompany.orderservice.param.OrderItemCreationRequest;
import com.mycompany.orderservice.service.OrderItemService;
import com.mycompany.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderAdapter orderAdapter;

    @Transactional
    @Override
    public void createOrder(Order order) {
        OrderModel orderModel = orderAdapter.fromDto(order);
        if (orderModel != null) {
            orderModel.setOrderDate(new Date());
            orderDao.save(orderModel);
            createOrderItems(orderModel.getOrderId(), order.getOrderItems());
        }
    }

    private void createOrderItems(Long orderId, List<OrderItem> orderItems) {
        OrderItemCreationRequest request = new OrderItemCreationRequest();
        request.setOrderId(orderId);
        request.setOrderItemModelList(orderItems);
        if (orderItemService.createOrderItems(request)) {
            log.info("Order items created");
        } else {
            log.info("Unable to create Order Items");
        }
    }


    @Transactional
    @Override
    public Order retrieveOrder(String customerName) {
        OrderModel orderModel = orderDao.findByCustomerName(customerName);
        Order order = null;
        if (orderModel != null) {
            order = orderAdapter.fromModel(orderModel);
            List<OrderItem> orderItems = orderItemService.getOrderItems(orderModel.getOrderId());
            order.setOrderItems(orderItems);
        } else {
            log.info("Order not found for customer name : {}", customerName);
        }
        return order;
    }
}
