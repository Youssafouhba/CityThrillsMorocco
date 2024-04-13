package com.CityThrillsMorocco.Agence.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgenceDto {
    private Long id;
    private String name;
    private String Location;
    private String email;
}
