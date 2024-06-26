package com.CityThrillsMorocco.Reservation.Controller;

import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.Reservation.Repository.ReservationRepository;
import com.CityThrillsMorocco.Reservation.Service.ReservationService;
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

    private final ReservationService reservationService;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    public ReservationController(ReservationService reservationService, JwtUtil jwtUtil, UserService userService) {
        this.reservationService = reservationService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userEmail = jwtUtil.extractUsername(token);
        return userService.searchByEmail(userEmail);
    }

    @PostMapping("/{activity_id}")
    public ResponseEntity<?> createReservation(@PathVariable("activity_id") Long activityId,@RequestBody User user,@RequestBody Reservation reservation) throws IOException {
        return this.reservationService.createReservation(user,reservation,activityId);
    }



}
