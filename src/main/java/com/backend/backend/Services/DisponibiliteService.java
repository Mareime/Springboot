package com.backend.backend.Services;

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
        // Validate medecin exists
        Optional<Medecin> medecin = medecinRepository.findById(disponibilite.getMedecin().getId());
        if (medecin.isEmpty()) {
            throw new RuntimeException("Medecin not found");
        }
        
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
    public List<Disponibilite> getDisponibilitesByJour(String jour) {
        return disponibiliteRepository.findByJour(jour);
    }
    
    // Update a disponibilite
    public Disponibilite updateDisponibilite(Long id, Disponibilite disponibiliteDetails) {
        Disponibilite disponibilite = disponibiliteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disponibilite not found"));
        
        disponibilite.setJour(disponibiliteDetails.getJour());
        disponibilite.setHeureDebut(disponibiliteDetails.getHeureDebut());
        disponibilite.setHeureFin(disponibiliteDetails.getHeureFin());
        disponibilite.setMedecin(disponibiliteDetails.getMedecin());
        
        return disponibiliteRepository.save(disponibilite);
    }
    
    // Delete a disponibilite
    public void deleteDisponibilite(Long id) {
        Disponibilite disponibilite = disponibiliteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disponibilite not found"));
        
        disponibiliteRepository.delete(disponibilite);
    }
}