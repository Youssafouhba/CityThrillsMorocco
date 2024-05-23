package com.CityThrillsMorocco.cart_element.controller;

import com.CityThrillsMorocco.Cart.Service.CartService;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.cart_element.service.CartElementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class CartElementController {
    @Autowired
    private CartElementService cartElementService;

    @Autowired
    private CartService cartService;
    @Autowired
    private ActivityService activityService;



}
