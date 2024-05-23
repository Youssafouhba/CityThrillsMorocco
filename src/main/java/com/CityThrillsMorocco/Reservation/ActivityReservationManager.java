package com.CityThrillsMorocco.Reservation;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor

public class ActivityReservationManager {

    private  final ActivityService activityService;
    public void checkAndUpdateActivityStatus() {
        LocalDate currentDate = LocalDate.now();
        Activity activity = activityService.getActivityById(5L);
        if (currentDate.isAfter(activity.getStartDate())) {
            activity.setStatus("Terminated");
            activityService.updateActivity(activity,5L);
        }
    }
}