package com.CityThrillsMorocco.Wishlist.repository;

import com.CityThrillsMorocco.Wishlist.model.Wishlist;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

     Wishlist findByUser(User user);

     @Query("SELECT w.activities FROM Wishlist w WHERE w.user.id = :userId")
     List<Activity> findActivityIdsByUserId(@Param("userId") Long userId);
}
