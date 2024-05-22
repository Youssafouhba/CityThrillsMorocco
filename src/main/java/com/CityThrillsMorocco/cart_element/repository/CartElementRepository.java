package com.CityThrillsMorocco.cart_element.repository;

import com.CityThrillsMorocco.Cart.Model.Cart;
import com.CityThrillsMorocco.cart_element.model.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartElementRepository extends JpaRepository<CartElement,Long> {
    void deleteById(Long id);



}
