package com.backend.backend.JwtModule.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private String sexe;
    private String role;

    // For Patient
    private Date dateNaissance;

    // For Medecin
    private String specialite;
    private Double prixConsultation;
    private String adresseCabinet;
}
