package com.CityThrillsMorocco.Wishlist.Dto;

import com.CityThrillsMorocco.activity.Dto.ActivityDto;
import com.CityThrillsMorocco.user.Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class WishlistDto {
    private Long id;
    private UserDto user;
    private List<ActivityDto> activities;
    // Getters et Setters
}