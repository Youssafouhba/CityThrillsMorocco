package com.CityThrillsMorocco.email.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    String firstName;
    String lastName;
    String email;
    Double total_amount;
    Map<String, Double> activities;


}
