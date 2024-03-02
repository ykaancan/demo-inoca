package com.kaancan.demoinoca.controller;

import com.kaancan.demoinoca.entity.Order;
import com.kaancan.demoinoca.entity.request.order.PlaceOrderRequest;
import com.kaancan.demoinoca.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/get-all-orders-for-customer/{customerId}")
    public ResponseEntity<List<Order>> getAllForCustomer(@PathVariable("customerId") UUID customerId) {
        List<Order> orderList = orderService.getAllForCustomer(customerId);
        return ResponseEntity.ok(orderList);
    }

    @PostMapping("/place")
    public ResponseEntity<HttpStatus> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest) {
        orderService.placeOrder(placeOrderRequest);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
