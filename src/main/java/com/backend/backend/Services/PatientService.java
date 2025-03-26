package com.backend.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Patient;
import com.backend.backend.Repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long id, Patient patient) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient updatedPatient = existingPatient.get();
            updatedPatient.setNom(patient.getNom());
            updatedPatient.setPrenom(patient.getPrenom());
            updatedPatient.setEmail(patient.getEmail());
            updatedPatient.setMotDePasse(patient.getMotDePasse());
            updatedPatient.setTel(patient.getTel());
            updatedPatient.setDateNaissance(patient.getDateNaissance());
            updatedPatient.setAdresse(patient.getAdresse());
            updatedPatient.setSexe(patient.getSexe());
            return patientRepository.save(updatedPatient);
        } else {
            return null; // or throw an exception
        }
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}