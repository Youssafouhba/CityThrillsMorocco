package com.CityThrillsMorocco.cart_element.dto;

import com.CityThrillsMorocco.activity.dtoSimple.ActivityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartElementDto {
    private Long id;
    private int nbrOfPerson;

    private ActivityDto activity;
    private Double sub_total;
}
