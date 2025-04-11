package com.backend.backend.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.Models.RendezVous;
import com.backend.backend.Services.RendezVousService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/rendezvous")
    public List<RendezVous> getAllRendezVous() {
        return rendezVousService.getAllRendezVous();
    }

    @GetMapping("/rendezvous/{id}")
    public Optional<RendezVous> getRendezVousById(@PathVariable Long id) {
        return rendezVousService.getRendezVousById(id);
    }

    @PostMapping("/rendezvous/add")
    public ResponseEntity<RendezVous> addRendezVous(@RequestBody RendezVous rendezVous) {
        RendezVous savedRendezVous = rendezVousService.addRendezVous(rendezVous);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRendezVous);
    }

    @PutMapping("/rendezvous/update/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable Long id, @RequestBody RendezVous rendezVous) {
        RendezVous updatedRendezVous = rendezVousService.updateRendezVous(id, rendezVous);
        if (updatedRendezVous != null) {
            return ResponseEntity.ok(updatedRendezVous);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }   

    @DeleteMapping("/rendezvous/{id}")
    public void deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
    }
}