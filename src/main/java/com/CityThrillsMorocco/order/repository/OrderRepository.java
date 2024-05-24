package com.CityThrillsMorocco.order.repository;

import com.CityThrillsMorocco.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
