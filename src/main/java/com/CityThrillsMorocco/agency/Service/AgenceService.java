package com.CityThrillsMorocco.agency.Service;

import com.CityThrillsMorocco.accountverification.Repository.ConfirmationTokenRepository;
import com.CityThrillsMorocco.accountverification.Service.EmailService;
import com.CityThrillsMorocco.agency.Dto.AgenceDto;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgenceService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AgenceRepository agenceRepository;
    private final ModelMapper mapper;
    private final EmailService emailService;


    public Agence searchByEmail(String email) {
        return agenceRepository.findByEmail(email);
    }

    public ResponseEntity<?> createAgence(AgenceDto agenceDto) throws NoSuchAlgorithmException {
        Agence agence = DtoToAgence(agenceDto);

        var existsEmail = agenceRepository.selectExistsEmail(agence.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + agence.getEmail() + " taken"
        );


        agenceRepository.save(agence);
        return ResponseEntity.ok("Created successfully");
    }


    public  ResponseEntity<List<AgenceDto>> getAllAgences(){
        var agences = new ArrayList<>( agenceRepository.findAll());
        return new ResponseEntity<>(agences.stream()
                .map(this::agenceToDto)
                .collect(Collectors.toList()),HttpStatus.FOUND);
    }

    public ResponseEntity<?> updateAgence(Long id, AgenceDto agenceDto)
            throws NoSuchAlgorithmException {
        var agence = findOrThrow(id);
        var agenceParam = DtoToAgence(agenceDto);
        agence.setEmail(agenceParam.getEmail());
        agence.setPhone(agenceParam.getPhone());
        agence.setLocation(agenceParam.getLocation());
        agence.setName(agenceParam.getName());
        agenceRepository.save(agence);
        return ResponseEntity.ok("updated succes");
    }

    public Agence getAgenceById(Long id){
        return agenceRepository
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("agence by id " + id + " was not found")
                );
    }

    public void DeleteAgenceById(Long id){
        findOrThrow(id);
        agenceRepository.deleteById(id);
    }


    public ResponseEntity<?> saveAgence(Agence agence) throws NoSuchAlgorithmException {
        if (agenceRepository.existsByEmail(agence.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        createAgence(agenceToDto(agence));

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }



    private Agence findOrThrow(final Long id) {
        return agenceRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("agence by id " + id + " was not found")
                );
    }


    public Long getTotalUserCountByAgency(Long agencyId) {
        return agenceRepository.countAgenceByIdIs(agencyId);
    }

    private AgenceDto agenceToDto(Agence agence) {
        return mapper.map(agence, AgenceDto.class);
    }

    private Agence DtoToAgence(AgenceDto agenceDto) {
        return mapper.map(agenceDto, Agence.class);
    }
}
