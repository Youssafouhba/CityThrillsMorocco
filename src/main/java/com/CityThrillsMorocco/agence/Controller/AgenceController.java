package com.CityThrillsMorocco.agence.Controller;

import com.CityThrillsMorocco.agence.Dto.AgenceDto;
import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.agence.Service.AgenceService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/CityThrillsMorocco/Admin")
public class AgenceController {
    private final AgenceService agenceService;
    @GetMapping("/all")
    public List<AgenceDto> AllUsers(){
        return agenceService.getAllAgences();
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerUser(@RequestBody Agence agence) {
        return agenceService.saveAgence(agence);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return agenceService.confirmEmail(confirmationToken);
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
    public void putAgence(
            @PathVariable("id") Long id,
            @RequestBody AgenceDto agenceDto
    ) throws NoSuchAlgorithmException {
        agenceService.updateAgence(id,agenceDto, agenceDto.getPassword());
    }


}
