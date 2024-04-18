package com.CityThrillsMorocco.agence.Repository;

import com.CityThrillsMorocco.agence.Model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Agence u " +
                    "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);
    Agence findByEmail(String email);
    Agence findByEmailIgnoreCase(String emailId);
    Boolean existsByEmail(String email);
}