package com.CityThrillsMorocco.Reservation.Controller;

import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.Reservation.Repository.ReservationRepository;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/CityThrillsMorocco/Admin/reservations")
public class ReservationController {
    private final UserService userService;
    private final ReservationRepository reservationRepository;
    private final ActivityRepo activityRepository;
    private final ModelMapper mapper;
    private final JwtUtil jwtUtil;

    public ReservationController(UserService userService, ReservationRepository reservationRepository, ActivityRepo activityRepository, ModelMapper mapper, JwtUtil jwtUtil) {
        this.userService = userService;
        this.reservationRepository = reservationRepository;
        this.activityRepository = activityRepository;
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/email")
    public String getUserEmail(@RequestHeader("Authorization") String token) {
        // Vérifier si le jeton commence par "Bearer " et le supprimer
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }



        // Obtenir l'adresse e-mail de l'utilisateur à partir des revendications (claims)
        String userEmail = jwtUtil.extractUsername(token);
        System.out.println(userEmail);
        return userEmail;
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> createReservation(@PathVariable("id") Long activityId) throws IOException {

            Activity activity = activityRepository.findById(activityId).orElse(null);
            if (activity != null) {
                // Vérifier si le nombre de participants est valide
                if (3 <= activity.getMaxParticipants()) {
                    // Créer la réservation
                    Reservation reservation = new Reservation();
                    reservation.setActivity(activity);
                    User user = mapper.map(userService.getUserById(20L),User.class);
                    reservation.setUser(user);
                    reservation.setParticipantCount(3);
                    // Enregistrer la réservation dans la base de données
                    reservationRepository.save(reservation);
                    return ResponseEntity.status(HttpStatus.CREATED).body("Réservation créée avec succès");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nombre de participants dépasse la capacité maximale de l'activité");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'activité spécifiée n'existe pas");
            }
    }



}
