package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "User details returned in API responses")
public class UserResponse {

    @Schema(description = "Unique user ID", example = "1")
    private Long userId;

    @Schema(description = "Full name", example = "Vansh Batham")
    private String name;

    @Schema(description = "Email address", example = "vansh@example.com")
    private String email;

    @Schema(description = "Assigned role", example = "SALES_REPRESENTATIVE")
    private String role;
}