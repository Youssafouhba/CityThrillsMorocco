package com.CityThrillsMorocco.Agence.Repository;

import com.CityThrillsMorocco.Agence.Model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Product u " +
                    "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);
    Agence findByEmail(String email);
}