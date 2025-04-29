// package com.backend.backend.JwtModule.dto;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// @Data
// @AllArgsConstructor
// public class AuthResponse {
//     private String token;
//     private String role;
// }
package com.backend.backend.JwtModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private final String token;
    private final String role;
    private final Long id;

    // Constructor and getters
    public AuthResponse(String token, String role, Long id) {
        this.token = token;
        this.role = role;
        this.id = id;
    }

    // Getters...
}
