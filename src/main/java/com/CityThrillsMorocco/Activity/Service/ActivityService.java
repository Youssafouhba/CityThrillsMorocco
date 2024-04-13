package com.CityThrillsMorocco.Activity.Service;

import com.CityThrillsMorocco.Activity.Model.Activity;
import com.CityThrillsMorocco.Activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.Agence.Model.Agence;
import com.CityThrillsMorocco.Agence.Service.AgenceService;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    private  final ActivityRepo activityRepo;
    private final AgenceService agenceService;
    public Agence addActivity(Activity activity, Long id){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) throw new BadRequestException(" this activity  already exists !!");
        Agence agence = agenceService.getAgenceById(id);
        activity.setAgence(agence);
        activityRepo.save(activity);
        return agence;
    }
    public void updateActivity(Long id, Activity activity1)
            throws NoSuchAlgorithmException {
        var activity = findOrThrow(id);
        activity.setDate(activity1.getDate());
        activity.setDescription(activity1.getDescription());
        activity.setDesignation(activity1.getDesignation());
        activity.setTarif(activity1.getTarif());
        activity.setCategory(activity1.getCategory());
        activity.setDuration(activity1.getDuration());
        activity.setVielle(activity1.getVielle());
        activity.setLocation(activity1.getLocation());
        activityRepo.save(activity);
    }
    public List<Activity> getAllActivities(){
        return new ArrayList<Activity>( activityRepo.findAll());
    }
    public Activity getActivityById(Long id){
        var activity = activityRepo
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Product by id " + id + " was not found")
                );
        return activity;
    }
    public void DeleteActivityById(Long id){
        findOrThrow(id);
        activityRepo.deleteById(id);
    }
    private Activity findOrThrow(final Long id) {
        return activityRepo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("activity by id " + id + " was not found")
                );
    }
}
