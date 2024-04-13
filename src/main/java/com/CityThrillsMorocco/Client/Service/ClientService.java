package com.CityThrillsMorocco.Client.Service;

import com.CityThrillsMorocco.Client.Dto.ClientDto;
import com.CityThrillsMorocco.Client.Model.Client;
import com.CityThrillsMorocco.Client.Repository.ClientRepository;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service

public class ClientService {

    private final ClientRepository clientRepositoryy;
    private final ModelMapper mapper;


    public Client createClient(ClientDto clientDto,String Password) throws NoSuchAlgorithmException {
        Client client = DtoToClient(clientDto);
        if (Password.isBlank()) throw new IllegalArgumentException(
                "Password is required"
        );
        var existsEmail = clientRepositoryy.selectExistsEmail(client.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + client.getEmail() + " taken"
        );
        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(client.getPassword(), salt);
        client.setStoredSalt(salt);
        client.setStoredHash(hashedPassword);
        clientRepositoryy.save(client);
        return client;
    }
    public void updateClient(Long id, ClientDto clientDto, String password)
            throws NoSuchAlgorithmException {
        var user = findOrThrow(id);
        var userParam = DtoToClient(clientDto);
        user.setEmail(userParam.getEmail());
        user.setPhone(userParam.getPhone());
        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);
            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }
        clientRepositoryy.save(user);
    }
    public List<ClientDto> getAllClients(){
        var clients = new ArrayList<>( clientRepositoryy.findAll());
        return clients.stream()
                .map(this::ClientToDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(Long id){
        var client = clientRepositoryy
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("User by id " + id + " was not found")
                );
        return ClientToDto(client);
    }

    public void DeleteClientById(Long id){
        findOrThrow(id);
        clientRepositoryy.deleteById(id);
    }

    private Client findOrThrow(final Long id) {
        return clientRepositoryy
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id " + id + " was not found")
                );
    }
    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);

        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    private ClientDto ClientToDto(Client client) {
        return mapper.map(client, ClientDto.class);
    }

    private Client DtoToClient(ClientDto clientDto) {
        return mapper.map(clientDto, Client.class);
    }
}
