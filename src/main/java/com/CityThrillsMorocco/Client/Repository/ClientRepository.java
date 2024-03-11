package com.CityThrillsMorocco.Client.Repository;

import com.CityThrillsMorocco.Client.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Client u " +
                    "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);
    Client findByEmail(String email);
}
