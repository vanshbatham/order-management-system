package com.oms.controller;

import com.oms.dto.request.SupplierRequest;
import com.oms.dto.response.SupplierResponse;
import com.oms.service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@Tag(name = "Supplier Management", description = "Create and view suppliers")
@SecurityRequirement(name = "Bearer Authentication")
public class SupplierController {

    private final PurchaseOrderService purchaseOrderService;

    @Operation(summary = "Create supplier", description = "Creates a new supplier. PURCHASING_OFFICER or ADMINISTRATOR.")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR')")
    public ResponseEntity<SupplierResponse> createSupplier(@Valid @RequestBody SupplierRequest request) {
        return new ResponseEntity<>(purchaseOrderService.createSupplier(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all suppliers")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST')")
    public ResponseEntity<List<SupplierResponse>> getAllSuppliers() {
        return new ResponseEntity<>(purchaseOrderService.getAllSuppliers(), HttpStatus.OK);
    }
}