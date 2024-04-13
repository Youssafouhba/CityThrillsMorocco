package com.CityThrillsMorocco.Agence.Service;

import com.CityThrillsMorocco.Agence.Model.Agence;
import com.CityThrillsMorocco.Agence.Repository.AgenceRepository;
import com.CityThrillsMorocco.Client.Dto.ClientDto;
import com.CityThrillsMorocco.Client.Model.Client;
import com.CityThrillsMorocco.Client.Service.ClientService;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgenceService {
    private final AgenceRepository agenceRepository;
    private final ModelMapper mapper;
    private final ClientService clientService;
    public Agence addAgence(Agence agence, Long id){
        var existingAgence = agenceRepository.selectExistsEmail(agence.getEmail());
        if(existingAgence) throw new BadRequestException(" this mail " + agence.getEmail() + " already exists !!");
        ClientDto client = clientService.getClientById(id);
        agence.setUser(mapper.map(client, Client.class));
        agenceRepository.save(agence);
        return agence;
    }
    public void updateAgence(Long id, Agence agence)
            throws NoSuchAlgorithmException {
        var agencee = findOrThrow(id);
        agencee.setUser(agencee.getUser());
        agencee.setEmail(agencee.getEmail());
        agencee.setLocation(agence.getLocation());
        agencee.setName(agence.getName());
        agenceRepository.save(agencee);
    }
    public List<Agence> getAllAgences(){
        return new ArrayList<Agence>( agenceRepository.findAll());
    }
    public Agence getAgenceById(Long id){
        var agence = agenceRepository
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("Product by id " + id + " was not found")
                );
        return agence;
    }
    public void DeleteAgenceById(Long id){
        findOrThrow(id);
        agenceRepository.deleteById(id);
    }
    private Agence findOrThrow(final Long id) {
        return agenceRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("agence by id " + id + " was not found")
                );
    }
}
