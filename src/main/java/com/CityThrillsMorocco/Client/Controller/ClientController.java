package com.CityThrillsMorocco.Client.Controller;


import com.CityThrillsMorocco.AccountVerification.Service.UserService;
import com.CityThrillsMorocco.Client.Dto.ClientDto;
import com.CityThrillsMorocco.Client.Model.Client;
import com.CityThrillsMorocco.Client.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/CityThrillsMorocco")
@CrossOrigin("http://localhost:4200/")

public class ClientController {

    private final ClientService clientService;
    @Autowired
    private UserService userService;

    @PostMapping("/registerc")
    public ResponseEntity<?> registerUser(@RequestBody Client user) {
        return userService.saveUser(user);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }


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
    public ClientDto getClientById(@PathVariable("id") Long id){
        return clientService.getClientById(id);
    }
    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClientById(@PathVariable("id") Long id){
        clientService.DeleteClientById(id);
    }

    @PutMapping("/update/{id}")
    public void putUser(
            @PathVariable("id") Long id,
            @RequestBody ClientDto clientDto
    ) throws NoSuchAlgorithmException {
        clientService.updateClient(id, clientDto, clientDto.getPassword());
    }
}
