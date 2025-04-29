package com.backend.backend.Controllers;

import java.sql.Date;
import java.time.LocalDate;
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

import com.backend.backend.Models.Disponibilite;
import com.backend.backend.Services.DisponibiliteService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api")
public class DisponibiliteController {

    @Autowired
    private DisponibiliteService disponibiliteService;

    @GetMapping("/disponibilites")
    public List<Disponibilite> getAllDisponibilites() {
        return disponibiliteService.getAllDisponibilites();
    }

    @GetMapping("/disponibilites/{id}")
    public Optional<Disponibilite> getDisponibiliteById(@PathVariable Long id) {
        return disponibiliteService.getDisponibiliteById(id);
    }

    @GetMapping("/disponibilites/medecin/{medecinId}")
    public List<Disponibilite> getDisponibilitesByMedecin(@PathVariable Long medecinId) {
        return disponibiliteService.getDisponibilitesByMedecin(medecinId);
    }

    @GetMapping("/disponibilites/jour/{jour}")
    public List<Disponibilite> getDisponibilitesByJour(@PathVariable LocalDate jour) {
        return disponibiliteService.getDisponibilitesByJour(jour);
    }

    @PostMapping("/disponibilites/add")
    public Disponibilite addDisponibilite(@RequestBody Disponibilite disponibilite) {
        disponibiliteService.createDisponibilite(disponibilite);
        return disponibilite;
    }

    @PutMapping("/disponibilites/update/{id}")
    public void updateDisponibilite(@PathVariable Long id, @RequestBody Disponibilite disponibilite) {
        disponibiliteService.updateDisponibilite(id, disponibilite);
    }

    @DeleteMapping("/disponibilites/{id}")
    public void deleteDisponibilite(@PathVariable Long id) {
        disponibiliteService.deleteDisponibilite(id);
    }
}
