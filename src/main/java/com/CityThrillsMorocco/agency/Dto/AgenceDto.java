package com.CityThrillsMorocco.agency.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgenceDto {
    private Long id;
    private String name;
    private String location;
    private String email;
    private String phone;
    private String password;
}
