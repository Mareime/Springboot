package com.backend.backend.repositories;

import com.backend.backend.Models.RendezVous;
import com.backend.backend.Models.Patient;
import com.backend.backend.Models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByPatient(Patient patient);

    List<RendezVous> findByMedecin(Medecin medecin);
}
