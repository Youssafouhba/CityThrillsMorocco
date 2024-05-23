package com.CityThrillsMorocco.user.repository;

import com.CityThrillsMorocco.user.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
