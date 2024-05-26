package com.CityThrillsMorocco.orderElement.repository;

import com.CityThrillsMorocco.cart_element.model.CartElement;
import com.CityThrillsMorocco.orderElement.model.OrderElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderElementRepository extends JpaRepository<OrderElement,Long> {
    List<OrderElement> getCartElementsByActivity_Id(Long activityId);
}
