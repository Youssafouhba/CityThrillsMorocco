package com.CityThrillsMorocco.agence.Service;

import com.CityThrillsMorocco.accountverification.Model.ConfirmationToken;
import com.CityThrillsMorocco.accountverification.Repository.ConfirmationTokenRepository;
import com.CityThrillsMorocco.accountverification.Service.EmailService;
import com.CityThrillsMorocco.agence.Dto.AgenceDto;
import com.CityThrillsMorocco.agence.Model.Agence;
import com.CityThrillsMorocco.agence.Repository.AgenceRepository;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

    public ResponseEntity<?>  createAgence(AgenceDto agenceDto, String Password) throws NoSuchAlgorithmException {
        Agence agence = DtoToAgence(agenceDto);
        if (Password.isBlank()) throw new IllegalArgumentException(
                "Password is required"
        );
        var existsEmail = agenceRepository.selectExistsEmail(agence.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + agence.getEmail() + " taken"
        );
        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(agence.getPassword(), salt);
        agence.setStoredSalt(salt);
        agence.setStoredHash(hashedPassword);
        agenceRepository.save(agence);
        return ResponseEntity.ok("Created successfully");
    }

    public List<AgenceDto> getAllAgences(){
        var agences = new ArrayList<>( agenceRepository.findAll());
        return agences.stream()
                .map(this::agenceToDto)
                .collect(Collectors.toList());
    }
    public void updateAgence(Long id, AgenceDto agenceDto, String password)
            throws NoSuchAlgorithmException {
        var agence = findOrThrow(id);
        var agenceParam = DtoToAgence(agenceDto);
        agence.setEmail(agenceParam.getEmail());
        agence.setPhone(agenceParam.getPhone());
        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);
            agence.setStoredSalt(salt);
            agence.setStoredHash(hashedPassword);
        }
        agenceRepository.save(agence);
    }
    public Agence getAgenceById(Long id){
        var agence = agenceRepository
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("agence by id " + id + " was not found")
                );
        return agence;
    }
    public void DeleteAgenceById(Long id){
        findOrThrow(id);
        agenceRepository.deleteById(id);
    }


    public ResponseEntity<?> saveAgence(Agence agence) throws NoSuchAlgorithmException {
        if (agenceRepository.existsByEmail(agence.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        createAgence(agenceToDto(agence), agence.getPassword());
        /*
        ConfirmationToken confirmationToken = new ConfirmationToken(agence);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(agence.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/CityThrillsMorocco/Admin/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
*/
        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }


    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken tokene = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(tokene != null)
        {
            Agence agence = agenceRepository.findByEmailIgnoreCase(tokene.getUser().getEmail());
            agence.setEnabled(true);
            agenceRepository.save(agence);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    private Agence findOrThrow(final Long id) {
        return agenceRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("agence by id " + id + " was not found")
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

    private AgenceDto agenceToDto(Agence agence) {
        return mapper.map(agence, AgenceDto.class);
    }

    private Agence DtoToAgence(AgenceDto agenceDto) {
        return mapper.map(agenceDto, Agence.class);
    }
}
