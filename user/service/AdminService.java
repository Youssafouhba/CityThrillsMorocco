package com.CityThrillsMorocco.user.service;

import com.CityThrillsMorocco.accountverification.Repository.ConfirmationTokenRepository;
import com.CityThrillsMorocco.activity.Repository.ActivityRepo;
import com.CityThrillsMorocco.agency.Repository.AgenceRepository;
import com.CityThrillsMorocco.user.repository.AdminRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final AgenceRepository agenceRepository;
    private final ActivityRepo activityRepo;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public AdminService(AdminRepository adminRepository, AgenceRepository agenceRepository, ActivityRepo activityRepo, ConfirmationTokenRepository confirmationTokenRepository) {
        this.adminRepository = adminRepository;
        this.agenceRepository = agenceRepository;
        this.activityRepo = activityRepo;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ResponseEntity<?> deleteAccoount(Long id){
        confirmationTokenRepository.delete(
                confirmationTokenRepository.findConfirmationTokenByUser(
                        adminRepository.getById(id)
                )
        );
        this.adminRepository.deleteById(id);
        return ResponseEntity.ok().body("Delete Pending ...");
    }
}
