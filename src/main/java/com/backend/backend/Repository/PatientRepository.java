package com.backend.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.backend.Models.Patient;
@Repository
public interface PatientRepository extends  JpaRepository<Patient, Long> {

}
