package com.CityThrillsMorocco.activity.dtoSimple;

import com.CityThrillsMorocco.enumeration.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityDto {
    private Long id;

    private String designation;

    private Double price;

    private String imageUrl;

    private City city;

}
