package com.mycompany.orderservice.client;

import com.mycompany.orderservice.model.OrderItem;
import com.mycompany.orderservice.param.OrderItemCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class OrderItemServiceClient {

    private static final String baseUrl = "http://localhost:8282/orderitem";

    @Autowired
    private RestTemplate restTemplate;

    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(baseUrl + "/" + orderId, HttpMethod.GET, entity, List.class).getBody();
    }

    public Boolean createOrderItems(OrderItemCreationRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<OrderItemCreationRequest> entity = new HttpEntity<>(request, headers);
        return restTemplate.exchange(baseUrl + "/create", HttpMethod.POST, entity, Boolean.class).getBody();
    }
}
