package com.CityThrillsMorocco.agency.Controller;

import com.CityThrillsMorocco.activity.Model.Activity;
import com.CityThrillsMorocco.agency.Service.AgenceService;
import com.CityThrillsMorocco.agency.Service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private AgenceService agenceService;
    private DashboardService dashboardService;

    @GetMapping("/{admin_id}")
    public ResponseEntity<?> AgencyClientsCount(@PathVariable("admin_id") Long id){
        return ResponseEntity.ok(dashboardService.getAgenceInfo(id));
    }

    @GetMapping("/agency/{id}/earnings")
    public ResponseEntity<List<DashboardService.ActivityEarningsDTO>> getAgencyEarnings(@PathVariable Long id) {
        List<DashboardService.ActivityEarningsDTO> earnings = dashboardService.getAgencyActivityEarnings(id);
        return ResponseEntity.ok(earnings);
    }

    @GetMapping("/agency/{id}/clients")
    public ResponseEntity<List<DashboardService.ActivityClientsDTO>> getAgencyActivityClients(@PathVariable Long id) {
        List<DashboardService.ActivityClientsDTO> clients = dashboardService.getAgencyActivityClients(id);
        return ResponseEntity.ok(clients);
    }
    @GetMapping("/agency-activities-by-category/{id}")
    public Map<String, Long> getAgencyActivitiesByCategory(@PathVariable Long id) {
        Map<String, Long> allActivities = dashboardService.getAgencyActivitiesByCategory(id);
        return allActivities;
    }
    @GetMapping("/agency/{id}/chart")
    public ResponseEntity<?> getAgencyActivityEarningslist(@PathVariable Long id) {
        List<Double> clients = dashboardService. getAgencyActivityEarningslist(id);
        return ResponseEntity.ok(clients);
    }
}