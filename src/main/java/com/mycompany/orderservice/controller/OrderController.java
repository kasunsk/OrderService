package com.mycompany.orderservice.controller;

import com.mycompany.orderservice.dto.Order;
import com.mycompany.orderservice.param.OrderResponse;
import com.mycompany.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{customerName}", method = RequestMethod.GET)
    public OrderResponse retrieveOrder(@PathVariable("customerName") String customerName) {
        return orderService.retrieveOrder(customerName);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Boolean createOrder(@RequestBody Order order) {
        orderService.createOrder(order);
        return Boolean.TRUE;
    }


}
