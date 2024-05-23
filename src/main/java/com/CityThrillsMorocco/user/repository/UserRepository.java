package com.CityThrillsMorocco.user.repository;

import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query(
          "" +
                  "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                  "TRUE ELSE FALSE END " +
                  "FROM User u " +
                  "WHERE u.email = ?1"
  )
  Boolean selectExistsEmail(String email);

  User findByEmail(String email);

  User findByEmailIgnoreCase(String emailId);

  Boolean existsByEmail(String email);

}
