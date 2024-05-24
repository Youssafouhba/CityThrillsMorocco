package com.CityThrillsMorocco.payment.repository;

import com.CityThrillsMorocco.payment.model.Payment;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT p FROM Payment p WHERE p.user.id = :userId ORDER BY p.creationDate DESC")
    List<Payment> findAllByUserIdOrderedByCreatiOrderByCreationDate(Long userId);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.user = :user")
    double sumAmountByUser(@Param("user") User user);


}
