package com.CityThrillsMorocco.Activity.Repository;

import com.CityThrillsMorocco.Activity.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

}
