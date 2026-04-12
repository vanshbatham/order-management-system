package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Response returned after successful login")
public class AuthResponse {

    @Schema(description = "JWT Bearer token", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "Full name of logged in user", example = "Vansh Batham")
    private String name;

    @Schema(description = "Email of logged in user", example = "vansh@example.com")
    private String email;

    @Schema(description = "Role of logged in user", example = "SALES_REPRESENTATIVE")
    private String role;
}