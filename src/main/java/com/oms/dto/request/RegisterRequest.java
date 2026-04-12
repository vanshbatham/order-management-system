package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for registering a new user")
public class RegisterRequest {

    @Schema(description = "Full name of the user", example = "Vansh Batham")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Email address of the user", example = "vansh@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Password (minimum 6 characters)", example = "secret123")
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Schema(description = "Role assigned to user", example = "SALES_REPRESENTATIVE",
            allowableValues = {
                    "ADMINISTRATOR", "BUSINESS_ANALYST",
                    "FINANCIAL_MANAGER", "PURCHASING_OFFICER",
                    "PRODUCT_MANAGER", "SALES_REPRESENTATIVE"
            })
    @NotBlank(message = "Role is required")
    private String role;
}