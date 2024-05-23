package com.CityThrillsMorocco.Program.Repository;

import com.CityThrillsMorocco.Program.Model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
}
