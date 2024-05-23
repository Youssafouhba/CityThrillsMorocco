package com.CityThrillsMorocco.jwt.models;


import com.CityThrillsMorocco.user.model.Admin;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserPrincipal implements UserDetails {


  private final User user;

  public UserPrincipal(User user){
      this.user = user;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();

    if (user instanceof Admin) {
      String role = "ROLE_CONTENT_MANAGER";
      authorities.add(new SimpleGrantedAuthority(role));

    }

    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
   return this.user.getEmail();
  }


  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

}
