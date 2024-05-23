package com.CityThrillsMorocco.agency.Controller;

import com.CityThrillsMorocco.agency.Dto.AgenceDto;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Service.AgenceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/Admin")
@CrossOrigin("http://localhost:4200/")
public class AgenceController {

    private final AgenceService agenceService;
    private final ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<AgenceDto>> AllUsers(){
        return agenceService.getAllAgences();
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable("id") Long id){
        return new  ResponseEntity<>(agenceService.getAgenceById(id),HttpStatus.FOUND);
    }*/


    @GetMapping("/{admin_id}")
    public ResponseEntity<Agence> getAgenceByAdmin(@PathVariable("admin_id") Long id){
        return ResponseEntity.ok(agenceService.getAgenceByUser(id));
    }

    @PostMapping
    public ResponseEntity<?> registerAgence(@RequestBody Agence agence) throws NoSuchAlgorithmException {
        return  agenceService.saveAgence(agence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgenceById(@PathVariable("id") Long id){
        agenceService.DeleteAgenceById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putAgence(@PathVariable("id") Long id, @RequestBody Agence agence ) throws NoSuchAlgorithmException {
        return ResponseEntity.ok(agenceService.updateAgence(id,mapper.map(agence,AgenceDto.class)));
    }

    @GetMapping("/users/count/{id}")
    public Long getTotalUserCountByAgency(@PathVariable("id") Long agencyId) {
        return agenceService.getTotalUserCountByAgency(agencyId);
    }

}
