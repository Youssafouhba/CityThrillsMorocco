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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final EmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

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

    public ResponseEntity<?> ResetPassword(String Email) throws NoSuchAlgorithmException {
        User user = searchByEmail(Email);
        var existsEmail = userRepository.selectExistsEmail(user.getEmail());
        if (!existsEmail) throw new BadRequestException(
                "Email " + user.getEmail() + "dosen't exist !!!"
        );
        ConfirmationToken confirmationToken =  confirmationTokenRepository.findConfirmationTokenByUser(user);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Rset Password!");
        mailMessage.setText("To Reset your Password, please click here : "
                +"http://localhost:4200/resetPassword?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    public ResponseEntity<?> registerNewPassword(String token ,String password) throws NoSuchAlgorithmException {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        User user = confirmationToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok("Password Created Succefully !!");
    }

    public User searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }




    public void updateUsert(Long id, UserDto userDto, String password)
            throws NoSuchAlgorithmException {
        var user = findOrThrow(id);
        var userParam = DtoToUser(userDto);
        user.setEmail(userParam.getEmail());
        user.setPhone(userParam.getPhone());
        if (!password.isBlank()) {
            user.setPassword(passwordEncoder.encode(password));
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

    private User findOrThrow(final Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id " + id + " was not found")
                );
    }

    public Long getTotalUserCount() {
        return userRepository.count();
    }

    private UserDto UserToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    private User DtoToUser(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }
}
