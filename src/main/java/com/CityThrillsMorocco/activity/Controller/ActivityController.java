package com.CityThrillsMorocco.activity.Controller;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/Admin/activities")
public class ActivityController {
    private final ActivityService activityService;

    @GetMapping("/all")
    public List<Activity> AllUsers(){
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public Activity getActivityById(@PathVariable("id") Long id){
        return activityService.getActivityById(id);
    }

    @PostMapping("/remove/{id}")
    public void deleteActivity(@PathVariable("id") Long id){
        activityService.deleteActivity(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> registerUser(@RequestBody Activity activity) throws NoSuchAlgorithmException {
        activityService.addActivity(activity,1L);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Message créé avec succès");
    }
}
