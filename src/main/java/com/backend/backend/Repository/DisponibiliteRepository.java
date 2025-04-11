package com.backend.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Models.Disponibilite;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite,Long> {

    List<Disponibilite> findByJour(String jour);

    List<Disponibilite> findByMedecinId(Long medecinId);
    
}
