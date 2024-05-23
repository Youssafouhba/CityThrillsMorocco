package com.CityThrillsMorocco.orderElement.repository;

import com.CityThrillsMorocco.orderElement.model.OrderElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderElementRepository extends JpaRepository<OrderElement,Long> {

}
