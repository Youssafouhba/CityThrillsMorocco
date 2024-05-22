package com.CityThrillsMorocco.cart_element.service;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.cart_element.repository.CartElementRepository;
import com.CityThrillsMorocco.exception.NotFoundException;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartElementService {

    @Autowired
    private CartElementRepository cartElementRepository;
    public CartElement addCartElement(Cart cart , Activity activity , int nbr_persons){
        System.out.println(activity.getDesignation());
        CartElement cartElement = new CartElement();
        cartElement.setCart(cart);
        cartElement.setActivity(activity);
        cartElement.setNbrOfPerson(nbr_persons);
        cartElement.setSub_total(nbr_persons * activity.getPrice());
        cart.setTotal_amount(cart.getTotal_amount() + cartElement.getSub_total());
        return cartElementRepository.save(cartElement);
    }


    public void deleteElementById(Long id) {
        System.out.println(id);
        cartElementRepository.deleteById(id);
    }

    public CartElement getCartElementById(Long id) {
        return cartElementRepository.findById(id).orElseThrow(() -> new NotFoundException("CartElement not found"));
    }





}
