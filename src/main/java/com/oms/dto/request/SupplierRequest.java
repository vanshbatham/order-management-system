package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for creating a new supplier")
public class SupplierRequest {

    @Schema(description = "Supplier company name", example = "PowerTech Supplies")
    @NotBlank(message = "Supplier name is required")
    private String name;

    @Schema(description = "Supplier email address", example = "supply@powertech.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Supplier phone number", example = "8888888888")
    private String phone;

    @Schema(description = "Supplier address", example = "Mumbai, Maharashtra")
    private String address;
}