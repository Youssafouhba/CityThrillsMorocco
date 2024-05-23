package com.CityThrillsMorocco.accountverification.Repository;

import com.CityThrillsMorocco.accountverification.Model.ConfirmationToken;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    ConfirmationToken findConfirmationTokenByUser(User user);
    @Query(
            "" +"DELETE FROM ConfirmationToken u WHERE u.user = ?1")
    void deleteConfirmationTokenByUser(User user);
}