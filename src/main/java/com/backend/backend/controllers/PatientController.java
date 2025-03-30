package com.backend.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.Models.Patient;
import com.backend.backend.services.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/profile")
    public Optional<Patient> getPatientProfile(@AuthenticationPrincipal String email) {
        return patientService.getProfile(email); // Récupère le profil du patient connecté
    }
}
