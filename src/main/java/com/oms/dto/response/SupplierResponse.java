package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Supplier details returned in API responses")
public class SupplierResponse {

    @Schema(description = "Unique supplier ID", example = "1")
    private Long supplierId;

    @Schema(description = "Supplier name", example = "PowerTech Supplies")
    private String name;

    @Schema(description = "Supplier email", example = "supply@powertech.com")
    private String email;

    @Schema(description = "Supplier phone", example = "8888888888")
    private String phone;

    @Schema(description = "Supplier address", example = "Mumbai, MH")
    private String address;
}