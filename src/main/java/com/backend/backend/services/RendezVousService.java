package com.backend.backend.services;

import com.backend.backend.Models.RendezVous;
import com.backend.backend.Models.Patient;
import com.backend.backend.Models.Medecin;
import com.backend.backend.repositories.RendezVousRepository;
import com.backend.backend.repositories.PatientRepository;
import com.backend.backend.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    // Récupérer les rendez-vous d'un patient
    public List<RendezVous> getRendezVousByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé"));
        return rendezVousRepository.findByPatient(patient);
    }

    // Récupérer les rendez-vous d'un médecin
    public List<RendezVous> getRendezVousByMedecin(Long medecinId) {
        Medecin medecin = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé"));
        return rendezVousRepository.findByMedecin(medecin);
    }

    // Créer un rendez-vous
    public RendezVous createRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }
}
