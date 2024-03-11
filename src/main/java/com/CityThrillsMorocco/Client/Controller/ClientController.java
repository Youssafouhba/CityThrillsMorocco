package com.CityThrillsMorocco.Client.Controller;


import com.CityThrillsMorocco.Client.Dto.ClientDto;
import com.CityThrillsMorocco.Client.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/CityThrillsMorocco")
@CrossOrigin("http://localhost:4200/")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public List<ClientDto> AllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void AddClient(@RequestBody ClientDto clientDto)
            throws NoSuchAlgorithmException {
        clientService.createClient(clientDto,clientDto.getPassword());
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id);
    }
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable("id") UUID id){
        clientService.DeleteClientById(id);
    }

    @PutMapping("/update/{id}")
    public void putUser(
            @PathVariable("id") UUID id,
            @RequestBody ClientDto clientDto
    ) throws NoSuchAlgorithmException {
        clientService.updateClient(id, clientDto, clientDto.getPassword());
    }
}
