package com.CityThrillsMorocco.Reservation;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@NoArgsConstructor
@Component
public class ActivityScheduler {

    private ActivityReservationManager reservationManager;
    private ScheduledExecutorService scheduler;

    public ActivityScheduler(ActivityReservationManager reservationManager) {
        this.reservationManager = reservationManager;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void startScheduling() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nextHourDateTime = currentDateTime.plusMinutes(1).withMinute(0).withSecond(0);

        long initialDelay = Duration.between(currentDateTime, nextHourDateTime).getSeconds();
        long period = 1; // Exécutez la tâche toutes les heures

        scheduler.scheduleAtFixedRate(this::checkAndUpdateActivityStatus, initialDelay, period, TimeUnit.HOURS);
    }

    private void checkAndUpdateActivityStatus() {
        reservationManager.checkAndUpdateActivityStatus();
    }

    public void stopScheduling() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        new ActivityScheduler().startScheduling();
    }
    // Autres méthodes de gestion du planning
}
