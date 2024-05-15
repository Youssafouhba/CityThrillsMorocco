package com.CityThrillsMorocco.RolesAndPrivileges.Repository;

import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleAdmin);

    Role findRoleByUsersIn(Collection<User> users);

}

