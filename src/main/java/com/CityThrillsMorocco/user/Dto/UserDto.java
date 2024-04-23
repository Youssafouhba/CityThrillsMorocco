package com.CityThrillsMorocco.user.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;


    {
        UserDto.builder()
                .email("dfgdfgdf")
                .firstname("ghhfghfgh")
                .build();
    }

}