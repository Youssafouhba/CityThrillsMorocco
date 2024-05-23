package com.CityThrillsMorocco.Cart.Repository;

import com.CityThrillsMorocco.Cart.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {

    Cart findByUserId(Long userId);
}
