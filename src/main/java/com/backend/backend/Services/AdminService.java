package com.backend.backend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.backend.Models.Admin;
import com.backend.backend.Repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public void updateAdmin(Long id, Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findById(id);
        if (existingAdmin.isPresent()) {
            Admin updatedAdmin = existingAdmin.get();
            updatedAdmin.setNom(admin.getNom());
            updatedAdmin.setPrenom(admin.getPrenom());
            updatedAdmin.setEmail(admin.getEmail());
            updatedAdmin.setMotDePasse(admin.getMotDePasse());
            updatedAdmin.setTelephone(admin.getTelephone());
            adminRepository.save(updatedAdmin);
        }
    }
}