package com.backend.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.Models.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
}