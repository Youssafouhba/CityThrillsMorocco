package com.CityThrillsMorocco.Agence.Repository;

import com.CityThrillsMorocco.Agence.Model.Agence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {

}