package com.CityThrillsMorocco.activity.Repository;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.enumeration.ActivityCategories;
import com.CityThrillsMorocco.enumeration.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
    @Query(
            "" +
                    "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Activity u " +
                    "WHERE u.designation = ?1"
    )
    Boolean selectExistsDesignation(String designation);
    Activity findByDesignation(String designation);
    List<Activity> findActivitiesByAgence_Id(Long agenceId);
    void deleteActivitiesByAgence_Id(Long agenceId);
    void deleteById(Long id);

    List<Activity> findAllByCategory(ActivityCategories category);

    List<Activity> findAllByCity(City city);

    int countByAgence(Agence agence);

}
