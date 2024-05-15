package com.CityThrillsMorocco.activity.Service;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.agency.Service.AgenceService;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final AgenceService agenceService;
    private final UserService userService;
    private final AgenceRepository agenceRepository;
    private final ModelMapper mapper;
    public List<Activity> getAllActivities(){
        List<Activity> activitieslist = new ArrayList<Activity>( activityRepo.findAll());
        return activitieslist;
    }

    public void deleteActivity(Long id){
         activityRepo.deleteById(id);
    }

    public ResponseEntity<?> addActivity(Activity activity, Long agenceid){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) {
            throw new BadRequestException(" this activity  already exists !!");
        }
        Agence agence = agenceService.getAgenceById(
                getAgenceByUser(agenceid));
        activity.setAgence(agence);
        activityRepo.save(activity);
        return ResponseEntity.ok(" added succesfully");
    }

    public void updateActivity(Activity activity,Long id){

        Activity activity1 = getActivityById(id);
        activity1.setAgence(agenceService.getAgenceById(activity1.getAgence().getId()));
        activity1.setPrice(activity.getPrice());
        activity1.setCategory(activity.getCategory());
        activity1.setDesignation(activity.getDesignation());
        activity1.setImageUrl(activity.getImageUrl());
        activity1.setPrice(activity.getPrice());
        activity1.setDescriptionDetail(activity.getDescriptionDetail());
        activity1.setAvailableYearRound(activity.isAvailableYearRound());
        activity1.setBookingEndDate(activity.getBookingEndDate());
        activity1.setBookingStartDate(activity.getBookingStartDate());
        activity1.setCity(activity.getCity());
        activity1.setPlacesLimited(activity.isPlacesLimited());
        activity1.setFlexibleDates(activity.isFlexibleDates());
        activity1.setMaxParticipants(activity.getMaxParticipants());
        activity1.setParticipants(activity.getParticipants());
        activityRepo.save(activity1);
    }

    public Activity getActivityById(Long id) {
        return activityRepo.getById(id);
    }

    public List<Activity> AllAgenceActivities(Long id){
        return activityRepo.findActivitiesByAgence_Id(
                getAgenceByUser(id)
        );
    }

    public Long getAgenceByUser(Long id){
        return agenceRepository.getAgenceByUsersIn(Arrays.asList(
                mapper.map(userService.getUserById(id), User.class)
        )).getId();
    }

}
