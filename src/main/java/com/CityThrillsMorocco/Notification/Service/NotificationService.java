package com.CityThrillsMorocco.Notification.Service;

import com.CityThrillsMorocco.Notification.Model.Notification;
import com.CityThrillsMorocco.Notification.Repository.NotificationRepository;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ActivityService activityService;

    public NotificationService(NotificationRepository notificationRepository, JwtUtil jwtUtil, UserService userService, ActivityService activityService) {
        this.notificationRepository = notificationRepository;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.activityService = activityService;
    }

    public void createNotification(String message,Long activityId,String token) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUser(getUser(token));
        notification.setActivity(activityService.getActivityById(activityId));
        notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public User getUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userEmail = jwtUtil.extractUsername(token);
        return userService.searchByEmail(userEmail);
    }

}
