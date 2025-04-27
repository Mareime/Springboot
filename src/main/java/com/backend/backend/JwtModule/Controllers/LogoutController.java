package com.backend.backend.JwtModule.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // With JWT, server-side logout is minimal since tokens are stateless
        // The actual invalidation happens on the client side
        return ResponseEntity.ok("Déconnexion réussie");
    }
}