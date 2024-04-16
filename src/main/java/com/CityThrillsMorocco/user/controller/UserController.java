package com.CityThrillsMorocco.user.controller;

import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
public class UserController {

  private final UserService userService;

  @GetMapping("/all")
  public List<UserDto> AllUsers(){
    return userService.getAllUserss();
  }


  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @GetMapping("/confirm-account")
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
    return userService.confirmEmail(confirmationToken);
  }



  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable("id") Long id){
    return userService.getUserById(id);
  }
  @GetMapping("/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUserById(@PathVariable("id") Long id){
    userService.DeleteUserById(id);
  }

  @PutMapping("/update/{id}")
  public void putUser(
          @PathVariable("id") Long id,
          @RequestBody UserDto userDto
  ) throws NoSuchAlgorithmException {
    userService.updateUsert(id,userDto, userDto.getPassword());
  }

}
