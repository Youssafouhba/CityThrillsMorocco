package com.CityThrillsMorocco.Cart.Service;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.Cart.Repository.CartRepo;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private final CartRepo cartRepo;

    @Autowired
    private ActivityService activityService ;

    public Cart addActivityToCart(Long cartId, Long activityId) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        Activity activity = activityService.getActivityById(activityId);

        if (cart != null && activity != null) {
            List<Activity> activityList = cart.getActivityList();
            activityList.add(activity);
            cart.setActivityList(activityList);
            return cartRepo.save(cart);
        } else {
            return null;
        }
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    public Optional<Cart> getCartById(Long id){
        return cartRepo.findById(id);
    }
}
