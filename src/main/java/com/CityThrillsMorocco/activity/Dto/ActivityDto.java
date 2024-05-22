package com.CityThrillsMorocco.activity.Dto;

import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private Long id;
    private String designation;
    private Date starteddate;

    private Date finisheddate;
    private String location;
    private String description;
    private Double price;
    private ActivityCategories category;
    private City city;
    private Long agence_id;

    private String imageUrl;
}
