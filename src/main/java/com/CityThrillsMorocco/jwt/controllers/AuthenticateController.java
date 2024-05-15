package com.CityThrillsMorocco.jwt.controllers;


import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.jwt.models.AuthenticationRequest;
import com.CityThrillsMorocco.jwt.models.AuthenticationResponse;
import com.CityThrillsMorocco.jwt.services.ApplicationUserDetailsService;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@AllArgsConstructor
public class AuthenticateController {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtTokenUtil;
  private final ApplicationUserDetailsService userDetailsService;


  @RequestMapping(value = "/authenticate")
  public ResponseEntity<?> authenticate(
    @RequestBody AuthenticationRequest req
  ) throws Exception {
    User user;
    try {
      user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
    } catch (BadCredentialsException e) {
      return ResponseEntity.badRequest().body("Incorrect username or password");
    }
    Field email = user.getClass().getDeclaredField("email");
    email.setAccessible(true);
    var userDetails = userDetailsService.loadUserByUsername((String) email.get(user));
    Role role = userDetailsService.getRole(user.getId());
    System.out.println(userDetails);
    var jwt = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok().body( new AuthenticationResponse(role,jwt,user.getId()));
  }

}
