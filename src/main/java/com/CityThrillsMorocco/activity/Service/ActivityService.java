package com.CityThrillsMorocco.activity.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.agence.Service.AgenceService;
import com.CityThrillsMorocco.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final AgenceService agenceService;

    public List<Activity> getAllActivities(){
        List<Activity> activities = new ArrayList<Activity>( activityRepo.findAll());
        return activities;
    }

    public void deleteActivity(Long id){
         activityRepo.deleteById(id);
    }

    public ResponseEntity<?> addActivity(Activity activity, Long agenceid){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) {
            throw new BadRequestException(" this activity  already exists !!");
        }
        Agence agence = agenceService.getAgenceById(agenceid);
        activity.setAgence(agence);
        activityRepo.save(activity);
        return ResponseEntity.ok(" added succesfully");
    }

    public void updateActivity(Activity activity,Long id){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) throw new BadRequestException(" this activity  already exists !!");

        Activity activity1 = getActivityById(id);

        activity1.setAgence(activity.getAgence());
        activity1.setPrice(activity.getPrice());
        activity1.setCategory(activity.getCategory());
        activity1.setAgence(activity.getAgence());
        activity1.setDesignation(activity.getDesignation());
        activity1.setImageUrl(activity.getImageUrl());
        activity1.setPrice(activity.getPrice());
        activity1.setDescriptiondetail(activity.getDescriptiondetail());
        activity1.setId(id);

        activityRepo.save(activity);
    }

    public Activity getActivityById(Long id) {
        return activityRepo.getById(id);
    }
}
