package com.CityThrillsMorocco.Cart.Controller;


import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/addActivity/{activityId}")
    public ResponseEntity<String> addActivityToCart(@PathVariable Long cartId, @PathVariable Long activityId) {
        Cart updatedCart = cartService.addActivityToCart(cartId, activityId);
        if (updatedCart != null) {
            return ResponseEntity.ok("Activity added to cart successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public Cart getCartByUserId(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }


}