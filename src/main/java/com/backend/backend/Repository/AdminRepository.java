package com.backend.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.backend.Models.Admin;

public interface  AdminRepository extends JpaRepository<Admin, Long> {
    
}
