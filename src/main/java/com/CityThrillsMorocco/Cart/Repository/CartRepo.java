package com.CityThrillsMorocco.Cart.Repository;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.user.model.Admin;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
    int countByUser(Admin admin);

    List<Cart> getCartsByUser(Admin admin);
}
