package com.CityThrillsMorocco.Reservation;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor

public class ActivityReservationManager {

    private  final ActivityService activityService;
    public void checkAndUpdateActivityStatus() {
        Date currentDate = new Date();
        Activity activity = activityService.getActivityById(5L);
        if (currentDate.after(activity.getStartDate())) {
            activity.setStatus("Terminated");
            activityService.updateActivity(activity, 5L);
        }
    }

}