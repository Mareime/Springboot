package com.backend.backend.Services;

import java.time.LocalDate;

import com.backend.backend.Models.Disponibilite;
import com.backend.backend.Models.Medecin;
import com.backend.backend.Repository.DisponibiliteRepository;
import com.backend.backend.Repository.MedecinRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibiliteService {

    @Autowired
    private DisponibiliteRepository disponibiliteRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    // Create a new disponibilite
    public Disponibilite createDisponibilite(Disponibilite disponibilite) {
        Long medecinId = disponibilite.getMedecin().getId();

        Medecin medecinComplet = medecinRepository.findById(medecinId)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé avec l'id : " + medecinId));

        disponibilite.setMedecin(medecinComplet);

        return disponibiliteRepository.save(disponibilite);
    }

    // Get all disponibilites
    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteRepository.findAll();
    }

    // Get disponibilite by ID
    public Optional<Disponibilite> getDisponibiliteById(Long id) {
        return disponibiliteRepository.findById(id);
    }

    // Get disponibilites by medecin ID
    public List<Disponibilite> getDisponibilitesByMedecin(Long medecinId) {
        return disponibiliteRepository.findByMedecinId(medecinId);
    }

    // Get disponibilites by day
    public List<Disponibilite> getDisponibilitesByJour(LocalDate jour) {
        return disponibiliteRepository.findByDate(jour);
    }

    // Update a disponibilite
    public Disponibilite updateDisponibilite(Long id, Disponibilite disponibiliteDetails) {
        // Recherche la disponibilite par ID
        Disponibilite disponibilite = disponibiliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilite not found"));

        // Met à jour les champs de la disponibilite
        disponibilite.setDate(disponibiliteDetails.getDate()); // Remplace 'jour' par 'date'
        disponibilite.setStartTime(disponibiliteDetails.getStartTime()); // Remplace 'heureDebut' par 'startTime'
        disponibilite.setEndTime(disponibiliteDetails.getEndTime()); // Remplace 'heureFin' par 'endTime'
        disponibilite.setMedecin(disponibiliteDetails.getMedecin()); // L'attribut medecin reste inchangé

        // Enregistre la disponibilite mise à jour dans la base de données
        return disponibiliteRepository.save(disponibilite);
    }

    // Delete a disponibilite
    public void deleteDisponibilite(Long id) {
        Disponibilite disponibilite = disponibiliteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilite not found"));

        disponibiliteRepository.delete(disponibilite);
    }
}
