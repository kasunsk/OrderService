package com.mycompany.orderservice.service.impl;

import com.mycompany.orderservice.adaptor.OrderAdapter;
import com.mycompany.orderservice.client.OrderItemServiceClient;
import com.mycompany.orderservice.dao.OrderDao;
import com.mycompany.orderservice.dto.Order;
import com.mycompany.orderservice.exception.ErrorCode;
import com.mycompany.orderservice.exception.ServiceRuntimeException;
import com.mycompany.orderservice.model.OrderItem;
import com.mycompany.orderservice.model.OrderModel;
import com.mycompany.orderservice.param.OrderItemCreationRequest;
import com.mycompany.orderservice.param.OrderResponse;
import com.mycompany.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

//    @Autowired
//    private OrderItemService orderItemService;

    @Autowired
    private OrderItemServiceClient orderItemServiceClient;

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
        if (!orderItemServiceClient.createOrderItems(request)) {
            throw new ServiceRuntimeException(ErrorCode.CAN_NOT_CREATE, "Unable to create Order Items");
        }
    }


    @Transactional
    @Override
    public OrderResponse retrieveOrder(String customerName) {
        OrderModel orderModel = orderDao.findByCustomerName(customerName);
        OrderResponse orderResp = null;
        if (orderModel != null) {
            orderResp = buildOrderResp(orderModel);
            List<OrderItem> orderItems = orderItemServiceClient.getOrderItemsByOrderId(orderModel.getOrderId());
            orderResp.setOrderItems(orderItems);
        } else {
            log.info("Order not found for customer name : {}", customerName);
            throw new ServiceRuntimeException(ErrorCode.NOT_FOUND, "Order not found for customer name " + customerName);
        }
        return orderResp;
    }

    private OrderResponse buildOrderResp(OrderModel order) {
        OrderResponse response = new OrderResponse();
        response.setCustomerName(order.getCustomerName());
        response.setOrderDate(toStringDate(order.getOrderDate()));
        response.setShippingAddress(order.getShippingAddress());
        response.setTotal(order.getTotal());
        return response;
    }

    private String toStringDate(Date orderDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(orderDate);
    }
}
