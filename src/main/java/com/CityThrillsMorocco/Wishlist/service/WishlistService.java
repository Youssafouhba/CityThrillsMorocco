package com.CityThrillsMorocco.Wishlist.service;

import com.CityThrillsMorocco.Wishlist.Dto.WishlistDto;
import com.CityThrillsMorocco.Wishlist.model.Wishlist;
import com.CityThrillsMorocco.Wishlist.repository.WishlistRepository;
import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {
    private WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ActivityRepo activityRepository;
    private final ActivityService activityService;
    private final ModelMapper modelMapper;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, UserRepository userRepository, ActivityRepo activityRepository, ModelMapper modelMapper,ActivityService activityService ) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.modelMapper=modelMapper;
        this.activityService=activityService;
    }

    public WishlistDto addToWishlist(Long userId, Long activityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Wishlist wishlist = user.getWishlist();
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setUser(user);
        }

        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new EntityNotFoundException("Activity not found with id: " + activityId));

        if (wishlist.getActivities().stream().noneMatch(a -> a.getId().equals(activityId))) {
            wishlist.getActivities().add(activity);
            wishlist = wishlistRepository.save(wishlist);
        }

        return modelMapper.map(wishlist, WishlistDto.class);
    }


    public List<ActivityDto> getWishlistActivities(Long userId) {
        List<Activity> activities = wishlistRepository.findActivityIdsByUserId(userId);
        return activities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public WishlistDto getWishlist(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Wishlist wishlist = user.getWishlist();
        return wishlist != null ? modelMapper.map(wishlist, WishlistDto.class) : null;
    }

    public boolean isActivityInWishlist(Long userId, Long activityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Wishlist wishlist = user.getWishlist();
        if (wishlist == null) {
            return false;
        }

        return wishlist.getActivities().stream().anyMatch(a -> a.getId().equals(activityId));
    }
    public WishlistDto removeFromWishlist(Long userId, Long activityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        Wishlist wishlist = user.getWishlist();
        if (wishlist == null) {
            throw new EntityNotFoundException("Wishlist not found for user with id: " + userId);
        }

        // Remove the activity from the wishlist
        wishlist.getActivities().removeIf(a -> a.getId().equals(activityId));

        // Save the updated wishlist
        wishlist = wishlistRepository.save(wishlist);

        return modelMapper.map(wishlist, WishlistDto.class);
    }
    public ActivityDto convertToDto(Activity activity) {
        ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
        activityDto.setAgence_id(activity.getAgence().getId());
        return activityDto;
    }

}
