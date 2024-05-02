package com.CityThrillsMorocco.activity.Controller;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
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

    @GetMapping
    public ResponseEntity<List<Activity>> AllUsers(){
        return new ResponseEntity<>(activityService.getAllActivities(),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable("id") Long id){
        return new ResponseEntity<>(activityService.getActivityById(id),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable("id") Long id){
        activityService.deleteActivity(id);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Activity activity) throws NoSuchAlgorithmException {
        activityService.addActivity(activity,3L);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Message créé avec succès");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(
            @RequestBody Activity activity,
            @PathVariable("id")Long id) throws NoSuchAlgorithmException {
        activityService.updateActivity(activity,id);
        return  ResponseEntity.status(HttpStatus.CREATED).body("Message créé avec succès");
    }

    @GetMapping("/activities/category{category}")
    public List<ActivityDto> getActivitiesByCategory(@PathVariable("category") ActivityCategories category) {
        return activityService.findAllByCategory(category);

    }

    @GetMapping("/activities/city/{city}")
    public List<ActivityDto> getActivitiesByCity(@PathVariable("city") City city){
        return activityService.findAllByCity(city);
    }

    @GetMapping("/activities")
    public List<Activity> getAllActivities(){
        return activityService.findall();
    }

}
