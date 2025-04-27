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
    private String token;
    private String role;
    private Long id; // <-- Add this field
}
