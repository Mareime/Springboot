package com.backend.backend.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Admin;
import com.backend.backend.Models.Medecin;
import com.backend.backend.Models.Patient;
import com.backend.backend.dto.LoginRequest;
import com.backend.backend.dto.RegisterRequest;
import com.backend.backend.repositories.AdminRepository;
import com.backend.backend.repositories.MedecinRepository;
import com.backend.backend.repositories.PatientRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PatientRepository patientRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder,
            AdminRepository adminRepository,
            MedecinRepository medecinRepository,
            PatientRepository patientRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
    }

    public Map<String, Object> loginUser(LoginRequest request) {
        // 1. Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotDePasse()));

        // 2. Get user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3. Build response
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Connexion réussie !");
        response.put("role", userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse(""));
        response.put("userDetails", userDetails);

        return response;
    }

    public Map<String, Object> registerUser(RegisterRequest request) {
        String email = request.getEmail();
        String role = request.getRole();
        String motDePasse = passwordEncoder.encode(request.getMotDePasse());
        String adresseCabinet = request.getAdresseCabinet();
        Double prixConsultation = request.getPrixConsultation();
        Date dateNaissance = request.getDateNaissance();
        String sexe = request.getSexe();

        if (adminRepository.findByEmail(email).isPresent() ||
                medecinRepository.findByEmail(email).isPresent() ||
                patientRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        if ("ADMIN".equalsIgnoreCase(role)) {
            Admin admin = new Admin();
            admin.setNom(request.getNom());
            admin.setPrenom(request.getPrenom());
            admin.setEmail(email);
            admin.setMotDePasse(motDePasse);
            admin.setTelephone(request.getTelephone());
            adminRepository.save(admin);
        } else if ("MEDECIN".equalsIgnoreCase(role)) {
            Medecin medecin = new Medecin();
            medecin.setNom(request.getNom());
            medecin.setPrenom(request.getPrenom());
            medecin.setEmail(email);
            medecin.setTelephone(request.getTelephone());
            medecin.setSpecialite(request.getSpecialite());
            medecin.setMotDePasse(motDePasse);
            medecin.setAdresseCabinet(adresseCabinet);
            medecin.setPrixConsultation(prixConsultation);
            medecinRepository.save(medecin);
        } else if ("PATIENT".equalsIgnoreCase(role)) {
            Patient patient = new Patient();
            patient.setNom(request.getNom());
            patient.setPrenom(request.getPrenom());
            patient.setEmail(email);
            patient.setMotDePasse(motDePasse);
            patient.setTel(request.getTel());
            patient.setAdresse(request.getAdresse());
            patient.setDateNaissance(dateNaissance);
            patient.setSexe(sexe);
            patientRepository.save(patient);
        } else {
            throw new RuntimeException("Rôle invalide !");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Inscription réussie !");
        response.put("role", role);

        return response;
    }

    private AuthenticationManager authenticationManager;

    public Map<String, Object> loginUser(LoginRequest request, HttpServletRequest httpRequest) {
        // 1. Authenticate through Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getMotDePasse()));

        // 2. Set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Create session explicitly
        HttpSession session = httpRequest.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        // 4. Get user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 5. Build response
        Map<String, Object> response = new HashMap<>();
        response.put("role", userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse(""));
        response.put("message", "Connexion réussie !");
        response.put("userDetails", userDetails);

        return response;
    }
}
