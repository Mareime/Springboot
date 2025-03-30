package com.backend.backend.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role;
    private String telephone;
    private String specialite;
    private String adresse;
    private int tel;
    private String adresseCabinet;
    private Double prixConsultation;
    private String sexe;
    private Date dateNaissance;

}
