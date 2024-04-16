package com.CityThrillsMorocco.jwt.controllers;


import com.CityThrillsMorocco.jwt.models.AuthenticationRequest;
import com.CityThrillsMorocco.jwt.models.AuthenticationResponse;
import com.CityThrillsMorocco.jwt.services.ApplicationUserDetailsService;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
class AuthenticateController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtTokenUtil;
  private final ApplicationUserDetailsService userDetailsService;

  @RequestMapping(value = "/authenticate")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthenticationResponse authenticate(
    @RequestBody AuthenticationRequest req
  ) throws Exception {
    User user;

    try {
      user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }

    var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

    System.out.println(userDetails);
    var jwt = jwtTokenUtil.generateToken(userDetails);

    return new AuthenticationResponse(jwt);
  }
}
