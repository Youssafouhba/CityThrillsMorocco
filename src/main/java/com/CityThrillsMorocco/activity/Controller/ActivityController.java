package com.CityThrillsMorocco.activity.Controller;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private final ActivityService activityService;

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

    @GetMapping("/{id}")
    public ActivityDto getActivity (@PathVariable("id") Long activityId){
        return activityService.getActivity(activityId);
    }
}
