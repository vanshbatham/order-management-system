package com.oms.controller;

import com.oms.dto.request.CreatePurchaseOrderRequest;
import com.oms.dto.response.PurchaseOrderResponse;
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
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
@Tag(name = "Purchase Order Management",
        description = "Create and manage purchase orders with suppliers")
@SecurityRequirement(name = "Bearer Authentication")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @Operation(summary = "Create purchase order", description = "Creates a new PO with a supplier. " +
            "PURCHASING_OFFICER or ADMINISTRATOR.")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR')")
    public ResponseEntity<PurchaseOrderResponse> createPurchaseOrder(@Valid @RequestBody CreatePurchaseOrderRequest request) {
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all purchase orders")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST', 'PRODUCT_MANAGER')")
    public ResponseEntity<List<PurchaseOrderResponse>> getAllPurchaseOrders() {
        return new ResponseEntity<>(purchaseOrderService.getAllPurchaseOrders(), HttpStatus.OK);
    }

    @Operation(summary = "Get purchase order by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST', 'PRODUCT_MANAGER')")
    public ResponseEntity<PurchaseOrderResponse> getPurchaseOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(purchaseOrderService.getPurchaseOrderById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update purchase order status", description = "Marking as RECEIVED auto-updates product stock."
            + " ADMINISTRATOR or PURCHASING_OFFICER.")
    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR')")
    public ResponseEntity<PurchaseOrderResponse> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return new ResponseEntity<>(purchaseOrderService.updateStatus(id, status), HttpStatus.OK);
    }

    @Operation(summary = "Cancel purchase order. ", description = "Cancels a PO and does not update stock."
            + " PURCHASING_OFFICER or ADMINISTRATOR.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PURCHASING_OFFICER', 'ADMINISTRATOR')")
    public ResponseEntity<String> cancelPurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.cancelPurchaseOrder(id);
        return new ResponseEntity<>("Purchase order cancelled successfully", HttpStatus.OK);
    }
}