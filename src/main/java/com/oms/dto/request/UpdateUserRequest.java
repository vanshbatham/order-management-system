package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for updating an existing user")
public class UpdateUserRequest {

    @Schema(description = "Updated full name", example = "Vansh Batham")
    private String name;

    @Schema(description = "Updated email address", example = "vansh.new@example.com")
    private String email;

    @Schema(description = "Updated role", example = "ADMINISTRATOR",
            allowableValues = {
                    "ADMINISTRATOR", "BUSINESS_ANALYST",
                    "FINANCIAL_MANAGER", "PURCHASING_OFFICER",
                    "PRODUCT_MANAGER", "SALES_REPRESENTATIVE"
            })
    private String role;
}