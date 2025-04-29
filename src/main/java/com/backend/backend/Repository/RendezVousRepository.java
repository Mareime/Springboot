package com.backend.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.backend.backend.Models.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    // Custom query to fetch RendezVous by medecinId
    List<RendezVous> findByMedecinId(Long medecinId);
}
