package com.CityThrillsMorocco.antiHero.repository;


import com.CityThrillsMorocco.antiHero.entity.AntiHeroEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {
}
