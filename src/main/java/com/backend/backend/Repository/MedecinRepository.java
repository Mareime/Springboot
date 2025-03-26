package com.backend.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.Models.Medecin;
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

}
