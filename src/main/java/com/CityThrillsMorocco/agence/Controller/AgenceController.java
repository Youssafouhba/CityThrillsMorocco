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
@CrossOrigin("http://localhost:54360/")
public class AgenceController {

    private final AgenceService agenceService;

    @GetMapping
    public ResponseEntity<List<AgenceDto>> AllUsers(){
        return agenceService.getAllAgences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> getAgenceById(@PathVariable("id") Long id){
        return new  ResponseEntity<>(agenceService.getAgenceById(id),HttpStatus.FOUND);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return agenceService.confirmEmail(confirmationToken);
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody Agence agence) throws NoSuchAlgorithmException {
        return  agenceService.saveAgence(agence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgenceById(@PathVariable("id") Long id){
        agenceService.DeleteAgenceById(id);
    }

    @PutMapping("/{id}")
    public void putAgence(@PathVariable("id") Long id, @RequestBody AgenceDto agenceDto ) throws NoSuchAlgorithmException {
        agenceService.updateAgence(id,agenceDto, agenceDto.getPassword());
    }

}
