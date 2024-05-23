package com.CityThrillsMorocco.Reservation.Service;

import com.CityThrillsMorocco.Reservation.Model.Reservation;
import com.CityThrillsMorocco.Reservation.Repository.ReservationRepository;
import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.activity.Service.ActivityService;
import com.CityThrillsMorocco.jwt.util.JwtUtil;
import com.CityThrillsMorocco.user.model.User;
import com.CityThrillsMorocco.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final ActivityService activityService;


    public List<Reservation> getReservationsByActivityId(Long activityId) {
        return reservationRepository.findByActivityId(activityId);
    }


    public ResponseEntity<?> createReservation(User  user, Reservation reservation, Long activityId){
        Activity activity = activityService.getActivityById(activityId);
        System.out.println(activity.getId());
        if(activity != null){
            //if (!verifMaxParticipants(reservation,activity))
              //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le nombre de participants dépasse la capacité maximale de l'activité");
            Reservation reservation_ = new Reservation();
            reservation_.setActivity(activity);
            reservation_.setUser(user);
            reservation_.setParticipantCount(reservation.getParticipantCount());
            reservation_.setEndDate(reservation.getEndDate());
            reservation_.setStartDate(reservation.getStartDate());
            reservationRepository.save(reservation_);
            return ResponseEntity.status(HttpStatus.CREATED).body("Réservation créée avec succès");

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'activité spécifiée n'existe pas");
        }
    }

    public User getUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userEmail = jwtUtil.extractUsername(token);
        return userService.searchByEmail(userEmail);
    }

    public boolean verifMaxParticipants(Reservation reservation,Activity activity){
        if (activity.getTotalParticipants() + reservation.getParticipantCount() <= activity.getMaxParticipants()) {
            return reservation.getParticipantCount() < activity.getParticipants();
        }
        return false;

    }
}
