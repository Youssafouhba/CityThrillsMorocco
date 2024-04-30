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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActivityService {
    @Autowired
    private final ActivityRepo activityRepo;
    private final AgenceService agenceService;
    @Autowired
    private final ModelMapper modelMapper;



    public Activity addActivity(Activity activity,Long agenceid){
        var existingActivity = activityRepo.selectExistsDesignation(activity.getDesignation());
        if(existingActivity) throw new BadRequestException(" this activity  already exists !!");
        Agence agence = agenceService.getAgenceById(agenceid);
        activity.setAgence(agence);
        activityRepo.save(activity);
        return activity;
    }


    public List<ActivityDto> findAllByCategory(ActivityCategories category) {
        List<Activity> activities = activityRepo.findAllByCategory(category);
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

    public List<Activity> findall(){
        return activityRepo.findAll();
    }

    public Activity getActivityById(Long activityId){
        return activityRepo.getById(activityId);
    }


    public ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }

    public ActivityDto getActivity(Long activityId) {
        Activity activity = activityRepo.getById(activityId);
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }
}
