package com.backend.backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.Models.Medecin;
import com.backend.backend.Services.MedecinService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping("/medecins")
    public List<Medecin> getAllMedecins() {
        return medecinService.getAllMedecins();
    }

    @GetMapping("/medecins/{id}")
    public Optional<Medecin> getMedecinById(@PathVariable Long id) {
        return medecinService.getMedecinById(id);
    }

    @PostMapping("/medecins/add")
    public void addMedecin(@RequestBody Medecin medecin) {
        medecinService.addMedecin(medecin);
    }

    @PutMapping("/medecins/update/{id}")
    public void updateMedecin(@PathVariable Long id, @RequestBody Medecin medecin) {
        medecinService.updateMedecin(id, medecin);
    }

    @DeleteMapping("/medecins/{id}")
    public void deleteMedecin(@PathVariable Long id) {
        medecinService.deleteMedecin(id);
    }

}
