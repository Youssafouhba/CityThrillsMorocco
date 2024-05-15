package com.CityThrillsMorocco.jwt.models;


import com.CityThrillsMorocco.RolesAndPrivileges.Repository.RoleRepository;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class UserPrincipal implements UserDetails {


  private final Object object;

  public UserPrincipal(Object object){
      this.object = object;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();

    if (object instanceof User user) {
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
    Class<?> clazz = object.getClass();
    try {
      Field emailField = clazz.getDeclaredField("email");
      emailField.setAccessible(true);
      return (String) emailField.get(object);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new RuntimeException(e);
    }
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
