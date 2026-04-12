package com.oms.controller;

import com.oms.dto.request.BOMRequest;
import com.oms.dto.response.BOMResponse;
import com.oms.service.BOMService;
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
@RequestMapping("/api/bom")
@RequiredArgsConstructor
@Tag(name = "BOM Management",
        description = "Bill of Materials — manage product components")
@SecurityRequirement(name = "Bearer Authentication")
public class BOMController {

    private final BOMService bomService;

    @Operation(summary = "Add component to BOM", description = "Adds a component to a product's bill of materials.")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER', 'ADMINISTRATOR')")
    public ResponseEntity<BOMResponse> addComponent(@Valid @RequestBody BOMRequest request) {
        return new ResponseEntity<>(bomService.addComponent(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get BOM for a product", description = "Returns all components required for a product.")
    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER', 'ADMINISTRATOR'," + "'SALES_REPRESENTATIVE', 'BUSINESS_ANALYST')")
    public ResponseEntity<List<BOMResponse>> getBOM(@PathVariable Long productId) {
        return new ResponseEntity<>(bomService.getBOMByProductId(productId), HttpStatus.OK);
    }

    @Operation(summary = "Remove component from BOM", description = "Removes a component entry from BOM by BOM ID.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER', 'ADMINISTRATOR')")
    public ResponseEntity<String> removeComponent(@PathVariable Long id) {
        bomService.removeComponent(id);
        return new ResponseEntity<>("Component removed from BOM successfully", HttpStatus.OK);
    }
}