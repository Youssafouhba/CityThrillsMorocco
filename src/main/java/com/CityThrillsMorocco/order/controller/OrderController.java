package com.CityThrillsMorocco.order.controller;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.order.model.Order;
import com.CityThrillsMorocco.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CityThrillsMorocco/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/{userId}")
    public Order display(@PathVariable("userId") Long userId){
        return orderService.createOrderFromCart(userId);
    }


}
