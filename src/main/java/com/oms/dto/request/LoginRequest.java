package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for user login")
public class LoginRequest {

    @Schema(description = "Registered email address", example = "vansh@example.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Account password", example = "secret123")
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(description = "Role selected from dropdown on frontend", example = "SALES_REPRESENTATIVE",
            allowableValues = {
                    "ADMINISTRATOR", "BUSINESS_ANALYST",
                    "FINANCIAL_MANAGER", "PURCHASING_OFFICER",
                    "PRODUCT_MANAGER", "SALES_REPRESENTATIVE"
            })
    @NotBlank(message = "Role is required")
    private String role;
}