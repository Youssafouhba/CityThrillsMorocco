package com.CityThrillsMorocco.Agence.Controller;

import com.CityThrillsMorocco.Agence.Model.Agence;
import com.CityThrillsMorocco.Agence.Service.AgenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/agence")
@CrossOrigin("http://localhost:4200/")
public class AgenceController {
    private final AgenceService agenceService;
    @PostMapping("/add")
    public Agence saveagence(@RequestBody Agence agence,Long id){
        return agenceService.addAgence(agence,id);
    }
    @GetMapping("/{id}")
    public Agence getAgenceById(@PathVariable("id") Long id){
        return agenceService.getAgenceById(id);
    }
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgenceById(@PathVariable("id") Long id){
        agenceService.DeleteAgenceById(id);
    }

    @PutMapping("/update/{id}")
    public void putUser(
            @PathVariable("id") Long id,
            @RequestBody Agence agence
    ) throws NoSuchAlgorithmException {
        agenceService.updateAgence(id,agence);
    }
}