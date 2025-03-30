package com.backend.backend.services;

// package com.backend.backend.services;

import com.backend.backend.repositories.AdminRepository;
import com.backend.backend.repositories.MedecinRepository;
import com.backend.backend.repositories.PatientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;

    public CustomUserDetailsService(AdminRepository adminRepository,
                                  MedecinRepository medecinRepository,
                                  PatientRepository patientRepository) {
        this.adminRepository = adminRepository;
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Check all repositories to find the user
        return adminRepository.findByEmail(email)
                .map(admin -> new CustomUserDetails(
                        admin.getEmail(),
                        admin.getMotDePasse(),
                        "ROLE_ADMIN"))
                .orElseGet(() -> medecinRepository.findByEmail(email)
                        .map(medecin -> new CustomUserDetails(
                                medecin.getEmail(),
                                medecin.getMotDePasse(),
                                "ROLE_MEDECIN"))
                        .orElseGet(() -> patientRepository.findByEmail(email)
                                .map(patient -> new CustomUserDetails(
                                        patient.getEmail(),
                                        patient.getMotDePasse(),
                                        "ROLE_PATIENT"))
                                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email))));
    }
}