package com.backend.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Patient;
import com.backend.backend.repositories.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Optional<Patient> getProfile(String email) {
        return patientRepository.findByEmail(email); // Retourne le patient selon l'email
    }
}
