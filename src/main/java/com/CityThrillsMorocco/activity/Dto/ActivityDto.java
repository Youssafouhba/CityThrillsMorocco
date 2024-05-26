package com.CityThrillsMorocco.activity.Dto;

import com.CityThrillsMorocco.enumeration.City;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityDto {
    private Long id;
    private String designation;
    private String description;
    private String descriptiondetail;
    private Date startDate;
    private Date endDate;
    private String location;
    private Double price;
    private String category;
    private String imageUrl;
    private String status;
    private int maxParticipants;
    private int totalParticipants;
    private int participants;
    private boolean isAvailableYearRound;
    private boolean isFlexibleDates;
    private boolean isPlacesLimited;
    private Date bookingStartDate;
    private Date bookingEndDate;
    private City city;
    private Long agence_id;
    private Long program_id;
}
