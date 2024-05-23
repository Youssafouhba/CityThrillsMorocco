package com.CityThrillsMorocco.activity.StatusUpdateTask;

import com.CityThrillsMorocco.WebSocket.WebSocketHandler;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ActivityStatusUpdateTask {


/*
    @Scheduled(fixedRate = 10000) // Exécution quotidienne à minuit
    public void updateActivityStatus() throws IOException {
        Iterable<Activity> activities1 = activityRepository.findAll();
        for (Activity activity : activities1) {
            activity.updateStatusWhenStartDateArrives();
            activityRepository.save(activity);
        }
        List<Activity> activities = activityRepository.findAll();
        activityWebSocketHandler.sendUpdateToClients(activities);
    }*/
}
