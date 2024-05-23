package com.CityThrillsMorocco.agency.Repository;

import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.user.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    List<Agence> findByAdmin(Admin admin);

    Long countAgenceByIdIs(Long id);
}
