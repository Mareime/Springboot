package com.backend.backend.controllers;

import com.backend.backend.Models.RendezVous;
import com.backend.backend.services.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    // Endpoint pour obtenir les rendez-vous d'un patient
    @GetMapping("/patient/{patientId}")
    public List<RendezVous> getRendezVousByPatient(@PathVariable Long patientId) {
        return rendezVousService.getRendezVousByPatient(patientId);
    }

    // Endpoint pour obtenir les rendez-vous d'un médecin
    @GetMapping("/medecin/{medecinId}")
    public List<RendezVous> getRendezVousByMedecin(@PathVariable Long medecinId) {
        return rendezVousService.getRendezVousByMedecin(medecinId);
    }

    // Endpoint pour créer un rendez-vous
    @PostMapping
    public RendezVous createRendezVous(@RequestBody RendezVous rendezVous) {
        return rendezVousService.createRendezVous(rendezVous);
    }
}
