package com.CityThrillsMorocco.Client.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {
    private UUID id;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String Password;
}