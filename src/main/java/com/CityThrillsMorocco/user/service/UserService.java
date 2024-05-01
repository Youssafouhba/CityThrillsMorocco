package com.CityThrillsMorocco.user.service;

import com.CityThrillsMorocco.accountverification.Model.ConfirmationToken;
import com.CityThrillsMorocco.accountverification.Repository.ConfirmationTokenRepository;
import com.CityThrillsMorocco.accountverification.Service.EmailService;
import com.CityThrillsMorocco.exception.BadRequestException;
import com.CityThrillsMorocco.exception.NotFoundException;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
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

public class UserService  {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public User searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User createUser(UserDto userDto, String Password) throws NoSuchAlgorithmException {
        User user = DtoToUser(userDto);
        if (Password.isBlank()) throw new IllegalArgumentException(
                "Password is required"
        );
        var existsEmail = userRepository.selectExistsEmail(user.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + user.getEmail() + " taken"
        );
        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(user.getPassword(), salt);
        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);
        userRepository.save(user);
        return user;
    }
    public void updateUsert(Long id, UserDto userDto, String password)
            throws NoSuchAlgorithmException {
        var user = findOrThrow(id);
        var userParam = DtoToUser(userDto);
        user.setEmail(userParam.getEmail());
        user.setPhone(userParam.getPhone());
        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);
            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }
        userRepository.save(user);
    }

    public List<UserDto> getAllUserss(){
        var users = new ArrayList<>( userRepository.findAll());
        return users.stream()
                .map(this::UserToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id){
        var user = userRepository
                .findById(id)
                .orElseThrow(
                        ()-> new NotFoundException("User by id " + id + " was not found")
                );
        return UserToDto(user);
    }

    public void DeleteUserById(Long id){
        findOrThrow(id);
        userRepository.deleteById(id);
    }


    public ResponseEntity<?> saveUser(User user) throws NoSuchAlgorithmException {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        createUser(mapper.map(user,UserDto.class),user.getPassword());

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/CityThrillsMorocco/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }


    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken tokene = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(tokene != null)
        {
            User user = userRepository.findByEmailIgnoreCase(tokene.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    private User findOrThrow(final Long id) {
        return userRepository
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

    private UserDto UserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    private User DtoToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
