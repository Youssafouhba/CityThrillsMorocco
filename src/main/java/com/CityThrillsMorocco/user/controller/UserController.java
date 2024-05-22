package com.CityThrillsMorocco.user.controller;

import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.AdminService;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco")
public class UserController {

  private final UserService userService;
  private final AdminService adminService;
  private final JwtUtil jwtUtil;

  @GetMapping
  public List<UserDto> AllUsers(){
    return userService.getAllUserss();
  }

  @GetMapping("/confirm-account")
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
    return userService.confirmEmail(confirmationToken);
  }

  @PostMapping("/Reset-password")
  public ResponseEntity<?> resetPassword(@RequestBody String email) throws NoSuchAlgorithmException {
    return userService.ResetPassword(email);
  }

  @PostMapping("/New_password")
  public ResponseEntity<?> newPassword(@RequestParam("token")String token,@RequestBody String password) throws NoSuchAlgorithmException {
    return userService.registerNewPassword(token,password);
  }

  @GetMapping("/{id}")
  public UserDto getUserById(@PathVariable("id") Long id){
    return userService.getUserById(id);
  }


  @DeleteMapping("/{admin_id}")
  public void deleteAccount(@PathVariable("admin_id") Long id){
    adminService.deleteAccoount(id);
  }

  @PutMapping("/{id}")
  public void putUser( @PathVariable("id") Long id, @RequestBody UserDto userDto ) throws NoSuchAlgorithmException {
    userService.updateUsert(id,userDto, userDto.getPassword());
  }

  @GetMapping("/count")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public Long getTotalUserCount() {
    return userService.getTotalUserCount();
  }


  public User getUser(String token) {
    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }
    String userEmail = jwtUtil.extractUsername(token);
    return userService.searchByEmail(userEmail);
  }


}
