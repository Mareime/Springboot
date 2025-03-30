package com.backend.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Medecin;
import com.backend.backend.repositories.MedecinRepository;

@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    public Optional<Medecin> getProfile(String email) {
        return medecinRepository.findByEmail(email); // Retourne le médecin selon l'email
    }

}
