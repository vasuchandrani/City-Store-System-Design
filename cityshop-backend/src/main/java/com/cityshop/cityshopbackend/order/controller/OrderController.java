package com.cityshop.cityshopbackend.order.controller;

import com.cityshop.cityshopbackend.order.entity.Order;
import com.cityshop.cityshopbackend.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cityshop/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Long cartId) {
        return orderService.placeOrder(cartId);
    }

}
