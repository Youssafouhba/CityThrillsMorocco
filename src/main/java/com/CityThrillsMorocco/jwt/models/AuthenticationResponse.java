package com.CityThrillsMorocco.jwt.models;

import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
  private Role role;
  private String token;
  private Long id;
}
