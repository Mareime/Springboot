package com.backend.backend.JwtModule.Controllers;

import com.backend.backend.JwtModule.dto.AuthRequest;
import com.backend.backend.JwtModule.dto.AuthResponse;
import com.backend.backend.JwtModule.dto.RegisterRequest;
import com.backend.backend.JwtModule.services.MyUserDetailsService;
import com.backend.backend.JwtModule.util.JwtUtil;
import com.backend.backend.Services.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
// public class AuthController {
//     private final AuthenticationManager authenticationManager;
//     private final MyUserDetailsService userDetailsService;
//     private final JwtUtil jwtUtil;
//     private final UserService userService;
//     @PostMapping("/login")
//     public AuthResponse login(@RequestBody AuthRequest request) {
//         Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));
//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//         String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
//         // Use email and role to generate token
//         String token = jwtUtil.generateToken(request.getEmail(), role);
//         return new AuthResponse(token, role);
//     }
//     @PostMapping("/register")
//     public String register(@RequestBody RegisterRequest request) {
//         userService.registerUser(request);
//         return "Utilisateur enregistré avec succès !";
//     }
// }
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse()));

        // Get user details and role
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        String email = userDetails.getUsername();

        // Generate the token
        String token = jwtUtil.generateToken(email, role);

        // Get the appropriate ID based on user role
        Long id = null;
        try {
            switch (role) {
                case "ROLE_PATIENT":
                    id = userService.getPatientIdByEmail(email);
                    break;
                case "ROLE_MEDECIN":
                    id = userService.getMedecinIdByEmail(email);
                    break;
                case "ROLE_ADMIN":
                    id = userService.getAdminIdByEmail(email);
                    break;
                default:
                    throw new RuntimeException("Rôle inconnu: " + role);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'ID: " + e.getMessage());
        }

        // Return the response with token, role, and id
        return new AuthResponse(token, role, id);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return "Utilisateur enregistré avec succès !";
    }
}
