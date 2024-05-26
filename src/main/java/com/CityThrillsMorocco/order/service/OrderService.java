package com.CityThrillsMorocco.order.service;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Service.CartService;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.order.model.Order;
import com.CityThrillsMorocco.order.repository.OrderRepository;
import com.CityThrillsMorocco.orderElement.model.OrderElement;
import com.CityThrillsMorocco.payment.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    public Order createOrderFromCart(Long userId){
        Cart cart = cartService.getCartByUserId(userId);
        System.out.println(cart.getId_Cart());
        Order order = new Order();
        order.setTotalAmount(cart.getTotal_amount());
        order.setUser(cart.getUser());
        List<OrderElement> orderItems = new ArrayList<>();
        for (CartElement cartElement : cart.getCartElements()) {
            OrderElement orderElement = new OrderElement();
            orderElement.setNbrOfPerson(cartElement.getNbrOfPerson());
            orderElement.setActivity(cartElement.getActivity());
            orderElement.setSub_total(cartElement.getSub_total());
            orderElement.setOrder(order);
            orderItems.add(orderElement);
        }
        order.setOrderItems(orderItems);
        order.setCreationDate(new Date());
        cartService.clearCart(cart);
        return orderRepository.save(order);
    }



}