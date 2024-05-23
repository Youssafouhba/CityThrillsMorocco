package com.CityThrillsMorocco.Wishlist.controller;


import com.CityThrillsMorocco.Wishlist.Dto.WishlistDto;
import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.Wishlist.model.Wishlist;
import com.CityThrillsMorocco.Wishlist.service.WishlistService;
import com.CityThrillsMorocco.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;



    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/add/{userid}/{activityid}")
    public ResponseEntity<WishlistDto> addToWishlist(@PathVariable("userid") Long userId,
                                                     @PathVariable("activityid") Long activityId) {
        WishlistDto wishlistDto = wishlistService.addToWishlist(userId, activityId);
        return new ResponseEntity<>(wishlistDto, HttpStatus.OK);
    }
    @GetMapping("/contains/{userid}/{activityid}")
    public ResponseEntity<Boolean> isActivityInWishlist(@PathVariable("userid") Long userId,
                                                        @PathVariable("activityid") Long activityId) {
        boolean isInWishlist = wishlistService.isActivityInWishlist(userId, activityId);
        return new ResponseEntity<>(isInWishlist, HttpStatus.OK);
    }

    @GetMapping("/wishlist/{userid}")
    public ResponseEntity<WishlistDto> getWishlist(@PathVariable("userid") Long userId) {
        WishlistDto wishlistDto = wishlistService.getWishlist(userId);
        return new ResponseEntity<>(wishlistDto, HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public List<ActivityDto> getActivities(@PathVariable("userid") Long userId){
        return wishlistService.getWishlistActivities(userId);
    }


    @DeleteMapping("/{userId}/{activityId}")
    public ResponseEntity<WishlistDto> removeFromWishlist(@PathVariable Long userId, @PathVariable Long activityId) {
        WishlistDto wishlistDto = wishlistService.removeFromWishlist(userId, activityId);
        return new ResponseEntity<>(wishlistDto, HttpStatus.OK);
    }
}
