package com.CityThrillsMorocco.jwt.services;


import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.RolesAndPrivileges.Repository.RoleRepository;
import com.CityThrillsMorocco.jwt.models.UserPrincipal;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

  private final UserService userService;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final ModelMapper mapper;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var userEntity = userService.searchByEmail(email);
    return new UserPrincipal(userEntity);
  }


  public User authenticate(String email, String password)
          throws NoSuchAlgorithmException {
    if (email.isEmpty() || password.isEmpty()) {
      throw new BadCredentialsException("Unauthorized");
    }

    var userEntity = userService.searchByEmail(email);

    if (userEntity == null) {
      throw new BadCredentialsException("Unauthorized");
    }

    if (!passwordEncoder.matches(password, userEntity.getPassword())) {
      throw new BadCredentialsException("Unauthorized");
    }
    return userEntity;

  }

  public Role getRole(Long id){
    return this.roleRepository.findRoleByUsersIn(Arrays.asList(mapper.map(userService.getUserById(id),User.class)));
  }
}
