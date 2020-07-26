package com.mycompany.orderservice.service;

import com.mycompany.orderservice.config.MyClientConfiguration;
import com.mycompany.orderservice.model.OrderItem;
import com.mycompany.orderservice.param.OrderItemCreationRequest;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// configuration = MyClientConfiguration.class
//@FeignClient(value = "order", url = "http://localhost:8282/orderitem", configuration = MyClientConfiguration.class)
public interface OrderItemService {

//    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}", produces = "application/json")
    List<OrderItem> getOrderItems(@PathVariable("orderId") Long orderId);

//    @RequestMapping(method = RequestMethod.POST, value = "/create", produces = "application/json")
    boolean createOrderItems(@RequestParam OrderItemCreationRequest request);
}
