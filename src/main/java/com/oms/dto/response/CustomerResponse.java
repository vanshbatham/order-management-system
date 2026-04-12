package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Customer details returned in API responses")
public class CustomerResponse {

    @Schema(description = "Unique customer ID", example = "1")
    private Long customerId;

    @Schema(description = "Customer name", example = "ABC Corp")
    private String name;

    @Schema(description = "Customer email", example = "abc@corp.com")
    private String email;

    @Schema(description = "Customer phone", example = "9999999999")
    private String phone;

    @Schema(description = "Customer address", example = "Indore, MP")
    private String address;
}