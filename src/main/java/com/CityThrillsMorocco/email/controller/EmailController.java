package com.CityThrillsMorocco.email.controller;


import com.CityThrillsMorocco.email.model.EmailDetails;
import com.CityThrillsMorocco.email.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailSender emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetails email){
        emailService.sendHtmlEmail(email);
        return ResponseEntity.ok("Email sent successfully");
    }
}
