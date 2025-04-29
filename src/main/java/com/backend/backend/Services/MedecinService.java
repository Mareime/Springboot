package com.backend.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Medecin;
import com.backend.backend.Repository.MedecinRepository;

@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    public Optional<Medecin> getMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    public Medecin addMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

    public void updateMedecin(Long id, Medecin medecin) {
        Optional<Medecin> existingMedecin = medecinRepository.findById(id);
        if (existingMedecin.isPresent()) {
            Medecin updatedMedecin = existingMedecin.get();
            updatedMedecin.setNom(medecin.getNom());
            updatedMedecin.setPrenom(medecin.getPrenom());
            updatedMedecin.setSpecialite(medecin.getSpecialite());
            updatedMedecin.setEmail(medecin.getEmail());
            updatedMedecin.setTelephone(medecin.getTelephone());
            updatedMedecin.setAdresseCabinet(medecin.getAdresseCabinet());
            updatedMedecin.setPrixConsultation(medecin.getPrixConsultation());
            medecinRepository.save(updatedMedecin);
        }
    }
}
