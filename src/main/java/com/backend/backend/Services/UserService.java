
package com.backend.backend.Services;

import com.backend.backend.JwtModule.dto.RegisterRequest;
import com.backend.backend.Models.Admin;
import com.backend.backend.Models.Medecin;
import com.backend.backend.Models.Patient;
import com.backend.backend.JwtModule.models.AppUser; // Import AppUser model
import com.backend.backend.JwtModule.repositories.UserRepository; // Import AppUser repository
import com.backend.backend.Repository.AdminRepository;
import com.backend.backend.Repository.MedecinRepository;
import com.backend.backend.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder; // Injected
    private final UserRepository appUserRepository; // Inject AppUserRepository

    public void registerUser(RegisterRequest request) {
        switch (request.getRole()) {
            case "PATIENT" -> registerPatient(request);
            case "MEDECIN" -> registerMedecin(request);
            case "ADMIN" -> registerAdmin(request);
            default -> throw new IllegalArgumentException("Rôle invalide.");
        }
    }

    private void registerPatient(RegisterRequest request) {
        // Register Patient
        Patient patient = new Patient();
        patient.setNom(request.getNom());
        patient.setPrenom(request.getPrenom());
        patient.setEmail(request.getEmail());
        patient.setMotDePasse(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash
        patient.settelephone(request.getTelephone());
        patient.setAdresse(request.getAdresse());
        patient.setSexe(request.getSexe());
        patient.setDateNaissance(request.getDateNaissance());
        patientRepository.save(patient);

        // Register AppUser for authentication
        AppUser appUser = new AppUser();
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash password
        appUser.setRole("PATIENT"); // Assign the role for the user
        appUserRepository.save(appUser); // Save AppUser for authentication
    }

    private void registerMedecin(RegisterRequest request) {
        // Register Medecin
        Medecin medecin = new Medecin();
        medecin.setNom(request.getNom());
        medecin.setPrenom(request.getPrenom());
        medecin.setEmail(request.getEmail());
        medecin.setMotDePasse(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash
        medecin.setTelephone(request.getTelephone());
        medecin.setAdresseCabinet(request.getAdresseCabinet());
        medecin.setSpecialite(request.getSpecialite());
        medecin.setPrixConsultation(request.getPrixConsultation());
        medecinRepository.save(medecin);

        // Register AppUser for authentication
        AppUser appUser = new AppUser();
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash password
        appUser.setRole("MEDECIN"); // Assign the role for the user
        appUserRepository.save(appUser); // Save AppUser for authentication
    }

    private void registerAdmin(RegisterRequest request) {
        // Register Admin
        Admin admin = new Admin();
        admin.setNom(request.getNom());
        admin.setPrenom(request.getPrenom());
        admin.setEmail(request.getEmail());
        admin.setMotDePasse(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash
        admin.setTelephone(request.getTelephone());
        adminRepository.save(admin);

        // Register AppUser for authentication
        AppUser appUser = new AppUser();
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getMotDePasse())); // 🔐 hash password
        appUser.setRole("ADMIN"); // Assign the role for the user
        appUserRepository.save(appUser); // Save AppUser for authentication
    }

    public Long getPatientIdByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient introuvable")).getId();
    }

    public Long getMedecinIdByEmail(String email) {
        return medecinRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Médecin introuvable")).getId();
    }

    public Long getAdminIdByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin introuvable")).getId();
    }

}
