package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for creating a new customer")
public class CustomerRequest {

    @Schema(description = "Customer company or person name", example = "ABC Corp")
    @NotBlank(message = "Customer name is required")
    private String name;

    @Schema(description = "Customer email address", example = "abc@corp.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Customer phone number", example = "9999999999")
    private String phone;

    @Schema(description = "Customer address", example = "Indore, Madhya Pradesh")
    private String address;
}