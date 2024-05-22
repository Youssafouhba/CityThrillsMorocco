package com.CityThrillsMorocco.Cart.Controller;


import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Service.CartService;
import com.CityThrillsMorocco.Cart.dto.CartDto;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.cart_element.service.CartElementService;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@NoArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @Autowired
    private CartElementService cartElementService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private  ModelMapper mapper;


    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {

        Cart cart = cartService.getCartByUserId(userId);

        if (cart == null) {
            UserDto user = userService.getUserById(userId);
            cart = cartService.createCartForUser(user);
        }

        return ResponseEntity.ok().body(mapper.map(cart,CartDto.class));
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<CartDto> getCartByUserId(@PathVariable Long userId) {
//
//        CartDto cart = mapper.map(cartService.getCartByUserId(userId),CartDto.class);
//
//        if (cart == null) {
//            UserDto user = userService.getUserById(userId);
//            cart = mapper.map(cartService.createCartForUser(user),CartDto.class);
//        }
//
//        return ResponseEntity.ok().body(cart);
//    }
//    @GetMapping("/{id}")
//    public Optional<Cart> getCartById(@PathVariable Long id) {
//        return cartService.getCartById(id);
//    }

    @PostMapping("/{activity_id}/addTo/{user_id}/{nbr}")
    public ResponseEntity<CartElement> addElementToCart(@PathVariable("activity_id") Long activityId, @PathVariable("user_id") Long userId, @PathVariable("nbr") int nbr){
        return ResponseEntity.ok( cartElementService.addCartElement(cartService.getCartByUserId(userId),activityService.getActivityById(activityId),nbr));
    }

    @DeleteMapping("/{element_id}/{cart_id}")
    public void deleteCartElement(@PathVariable("element_id") Long element_id ,@PathVariable("cart_id") Long cart_id){
        cartService.removeCartItem(element_id,cart_id);
    }

}