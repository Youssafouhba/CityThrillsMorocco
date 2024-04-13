package com.CityThrillsMorocco.Client.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
}