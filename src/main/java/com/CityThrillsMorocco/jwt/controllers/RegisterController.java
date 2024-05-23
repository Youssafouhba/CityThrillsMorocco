package com.CityThrillsMorocco.jwt.controllers;

import com.CityThrillsMorocco.exception.EmailExistsException;
import com.CityThrillsMorocco.jwt.services.RegisterUserDetailsService;
import com.CityThrillsMorocco.user.Dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value="/CityThrillsMorocco/register")
@AllArgsConstructor
public class RegisterController {
    private final RegisterUserDetailsService registerUserDetailsService;

    @PostMapping("/{roleName}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody UserDto user,@PathVariable("roleName") String roleName) throws EmailExistsException, NoSuchAlgorithmException {
        return registerUserDetailsService.registerNewUserAccount(user,roleName);
    }
}
