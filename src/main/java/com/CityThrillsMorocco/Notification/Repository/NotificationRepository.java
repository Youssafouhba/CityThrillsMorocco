package com.CityThrillsMorocco.Notification.Repository;

import com.CityThrillsMorocco.Notification.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
