package com.CityThrillsMorocco.Notification.Controller;

import com.CityThrillsMorocco.Notification.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<?> createNotification(@RequestHeader("Authorization") String token, @RequestParam String message, @RequestParam Long activityId) {
        notificationService.createNotification(message,activityId,token);
        return ResponseEntity.ok().body("Notification created successfully");
    }

}
