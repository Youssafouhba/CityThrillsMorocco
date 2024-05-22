package com.CityThrillsMorocco.activity.Service;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.agence.Service.AgenceService;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
import com.CityThrillsMorocco.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final AgenceService agenceService;
    private final ModelMapper modelMapper;

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

    public List<ActivityDto> findAllByCategory(ActivityCategories category) {
        List<Activity> activities = activityRepo.findAllByCategory(category);
        return activities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ActivityDto> findFamousPlaces() {
        List<Activity> activities = activityRepo.findFamousPlaces();

        if (activities.size() > 6) {
            activities = activities.subList(0, 6);
        }

        return activities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    public List<ActivityDto> findAllByCity(City city){
        List<Activity> activities = activityRepo.findAllByCity(city);
        return activities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Activity getActivityById(Long id) {
        return activityRepo.getById(id);
    }

    public List<Activity> findall(){
        return activityRepo.findAll();
    }



    public ActivityDto findById(Long activityId) {
        Activity activity = activityRepo.getById(activityId);
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }
    public int countActivities(){
        return activityRepo.countActivities();
    }
    public List<ActivityDto> findActivitiesByCityAndCategory(City city, String category) {
        List<Activity> activities = activityRepo.findActivitiesByCityAndCategory(city, category);
        List<ActivityDto> activityDtos = new ArrayList<>();
        for (Activity activity : activities) {
            ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
            activityDtos.add(activityDto);
        }
        return activityDtos;
    }
    public ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }
}
