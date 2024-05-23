package com.CityThrillsMorocco.cart_element.repository;

import com.CityThrillsMorocco.cart_element.model.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartElementRepository extends JpaRepository<CartElement,Long> {
    void deleteById(Long id);



}
