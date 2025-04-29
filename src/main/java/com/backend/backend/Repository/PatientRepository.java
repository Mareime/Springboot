package com.backend.backend.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.backend.Models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPrenom(String prenom);
    // Cette requête native suppose qu'il existe une table rendez_vous qui lie patients et médecins

    @Query(value = "SELECT p.* FROM patient p JOIN rendez_vous r ON p.id = r.patient_id WHERE r.medecin_id = :medecinId GROUP BY p.id", nativeQuery = true)
    List<Patient> findPatientsByMedecinId(@Param("medecinId") Long medecinId);

}
