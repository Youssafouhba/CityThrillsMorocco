package com.CityThrillsMorocco.View.Repository;

import com.CityThrillsMorocco.View.Model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ViewRepository extends JpaRepository<View,Long> {
    Long countByDate(LocalDate today);
}
