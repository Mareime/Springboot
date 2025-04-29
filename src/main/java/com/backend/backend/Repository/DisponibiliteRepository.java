package com.backend.backend.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Models.Disponibilite;

public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {

    List<Disponibilite> findByDate(LocalDate date);

    List<Disponibilite> findByMedecinId(Long medecinId);

}
