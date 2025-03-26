package com.backend.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.RendezVous;
import com.backend.backend.Repository.RendezVousRepository;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    public Optional<RendezVous> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id);
    }

    public RendezVous addRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    public void deleteRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }

    public RendezVous updateRendezVous(Long id, RendezVous rendezVous) {
        Optional<RendezVous> existingRendezVous = rendezVousRepository.findById(id);
        if (existingRendezVous.isPresent()) {
            RendezVous updatedRendezVous = existingRendezVous.get();
            updatedRendezVous.setDateRdv(rendezVous.getDateRdv());
            updatedRendezVous.setStatut(rendezVous.getStatut());
            updatedRendezVous.setMotif(rendezVous.getMotif());
            updatedRendezVous.setPatient(rendezVous.getPatient());
            updatedRendezVous.setMedecin(rendezVous.getMedecin());
            return rendezVousRepository.save(updatedRendezVous);
        } else {
            return null; // or throw an exception
        }
    }
}