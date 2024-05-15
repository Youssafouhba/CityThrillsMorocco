package com.CityThrillsMorocco.RolesAndPrivileges.Repository;

import com.CityThrillsMorocco.RolesAndPrivileges.Models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByName(String name);
}
