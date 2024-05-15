package com.CityThrillsMorocco.agency.Repository;

import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.user.model.User;
import org.hibernate.mapping.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

    Agence getAgenceByUsersIn(Collection<User> users);

    Long countAgenceByIdIs(Long id);
}
