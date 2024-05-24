package com.CityThrillsMorocco.agency.Controller;

import com.CityThrillsMorocco.agency.Service.AgenceService;
import com.CityThrillsMorocco.agency.Service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private AgenceService agenceService;
    private DashboardService dashboardService;

    @GetMapping("/{admin_id}")
    public ResponseEntity<?> AgencyClientsCount(@PathVariable("admin_id") Long id){
        return ResponseEntity.ok(dashboardService.getAgenceInfo(id));
    }
}
