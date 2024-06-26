package com.CityThrillsMorocco.Cart.Service;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Repository.CartRepo;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.cart_element.repository.CartElementRepository;
import com.CityThrillsMorocco.cart_element.service.CartElementService;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

@AllArgsConstructor
public class CartService {

    @Autowired
    private final CartRepo cartRepo;

    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private CartElementService cartElementService;
    @Autowired
    private UserService userService;

//    public Cart addActivityToCart(Long cartId, Long activityId) {
//        Cart cart = cartRepo.findById(cartId).orElse(null);
//        Activity activity = activityService.getActivityById(activityId);
//
//        if (cart != null && activity != null) {
//            List<Activity> activityList = cart.getActivityList();
//            activityList.add(activity);
//            cart.setActivityList(activityList);
//            return cartRepo.save(cart);
//        } else {
//            return null;
//        }
//    }

    public Cart getCartByUserId(Long userId) {
        Cart cart=cartRepo.findByUserId(userId);
        if(cart ==null){
            cart=new Cart();
            cart.setUser(mapper.map(userService.getUserById(userId),User.class));
            cart.setTotal_amount(0.0);
            cartRepo.save(cart);
        }
        return cart;
    }


    public Optional<Cart> getCartById(Long id){
        return cartRepo.findById(id);
    }

    public Cart createCartForUser(UserDto user){
        Cart cart = new Cart();
        cart.setTotal_amount(0.0);
        cart.setUser(mapper.map(user, User.class));
        return cartRepo.save(cart);
    }

    public void removeCartItem(Long elementId ,Long cartId) {
        System.out.println(elementId+"--->cart"+cartId );
        Cart cart = cartRepo.findById(cartId).orElse(null);
        CartElement cartElement = cartElementService.getCartElementById(elementId);
        cart.setTotal_amount(cart.getTotal_amount()-cartElement.getSub_total());
        cartRepo.save(cart);
        cartElementService.deleteElementById(cartElement.getId());

    }
    @Transactional
    public void clearCart(Cart cart){
        cartRepo.delete(cart);
    }


}