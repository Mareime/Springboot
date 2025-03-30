package com.backend.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.Models.Medecin;
import com.backend.backend.services.MedecinService;

@RestController
@RequestMapping("/api/medecin")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping("/profile")
    public Optional<Medecin> getMedecinProfile(@AuthenticationPrincipal String email) {
        return medecinService.getProfile(email); 
    }
}
