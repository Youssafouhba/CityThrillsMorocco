package com.CityThrillsMorocco.jwt.services;

import com.CityThrillsMorocco.accountverification.Model.ConfirmationToken;
import com.CityThrillsMorocco.accountverification.Repository.ConfirmationTokenRepository;
import com.CityThrillsMorocco.accountverification.Service.EmailService;
import com.CityThrillsMorocco.agency.Model.Agence;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.exception.EmailExistsException;
import com.CityThrillsMorocco.RolesAndPrivileges.Models.Privilege;
import com.CityThrillsMorocco.RolesAndPrivileges.Repository.RoleRepository;
import com.CityThrillsMorocco.RolesAndPrivileges.Models.Role;
import com.CityThrillsMorocco.user.Dto.UserDto;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.repository.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("userDetailsService")
@Transactional
public class RegisterUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AgenceRepository agenceRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    private final MessageSource messages;

    private final RoleRepository roleRepository;

    public RegisterUserDetailsService(UserRepository userRepository, AgenceRepository agenceRepository, PasswordEncoder passwordEncoder, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService, MessageSource messages, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.agenceRepository = agenceRepository;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
        this.messages = messages;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> registerNewUserAccount(UserDto accountDto,String roleName) throws EmailExistsException, NoSuchAlgorithmException {

        if (emailExist(accountDto.getEmail())) {
           return ResponseEntity.badRequest().body("There is an account with that email address:" + accountDto.getEmail());
        }

        User user = new User();
        user.setFirstname(accountDto.getFirstname());
        user.setLastname(accountDto.getLastname());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setPhone(accountDto.getPhone());
        user.setRoles(Arrays.asList(roleRepository.findByName(roleName)));

        if(roleName == "ROLE_CONTENT_MANAGER"){
            Agence agency = new Agence();
            Set<Agence> agences = new HashSet<>();
            agency.setName(user.getFirstname());
            agency.setPhone(user.getPhone());
            agency.setLocation(user.getLastname());
            agency.setEmail(user.getEmail());
            agenceRepository.save(agency);
            agences.add(agenceRepository.findByEmail(agency.getEmail()));
            user.setAgences(agences);
        }
        userRepository.save(user);
        SendMail(user);

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    public ResponseEntity<?> SendMail(User user) throws NoSuchAlgorithmException {
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:4200/Admin_sign_up?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());
        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                    " ", " ", true, true, true, true,
                    getAuthorities(Arrays.asList(
                            roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
                true, getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
