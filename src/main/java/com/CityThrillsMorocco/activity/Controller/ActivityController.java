package com.CityThrillsMorocco.activity.Controller;


import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.activity.WebSocket.ActivityWebSocketHandler;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/activities")
@CrossOrigin("http://localhost:4200/")
public class ActivityController {
    private final ActivityWebSocketHandler activityWebSocketHandler;
    private final ActivityService activityService;
    public static List<Activity> activities  = new ArrayList<>();
    public static List<Activity> activities$  = new ArrayList<>();

    public ActivityController(ActivityWebSocketHandler activityWebSocketHandler, ActivityService activityService){
        this.activityWebSocketHandler = activityWebSocketHandler;
        //activities=activityService.getAllActivities();
        this.activityService = activityService;
    }

    @GetMapping
    public ResponseEntity<List<Activity>> allAgences() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    @GetMapping("/agence/{agence_id}")
    public ResponseEntity<List<Activity>> AllAgenceActivities(@PathVariable ("agence_id") Long id) throws IOException {
        //activities = activityService.getAllActivities();

        return new ResponseEntity<>(activityService.AllAgenceActivities(id),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activityService.getActivityById(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<?>  deleteActivity(@PathVariable("id") Long id) throws IOException {
        activityService.deleteActivity(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Deleted succès");
    }

    @PostMapping("/{agence_id}")
    //@PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<?> registerUser(@RequestBody Activity activity,@PathVariable("agence_id") Long agence_id) throws  IOException {
        activityService.addActivity(activity,agence_id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Activité ajoutée avec succès");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_CONTENT_MANAGER')")
    public ResponseEntity<?> updateActivity(
            @RequestBody Activity activity,
            @PathVariable("id")Long id) throws IOException {
        activityService.updateActivity(activity,id);

        return  ResponseEntity.status(HttpStatus.CREATED).body("Message créé avec succès");
    }
    @GetMapping("/category{category}")
    public List<ActivityDto> getActivitiesByCategory(@PathVariable("category") ActivityCategories category) {
        return activityService.findAllByCategory(category);

    }

    @GetMapping("/city/{city}")
    public List<ActivityDto> getActivitiesByCity(@PathVariable("city") City city){
        return activityService.findAllByCity(city);
    }

}
