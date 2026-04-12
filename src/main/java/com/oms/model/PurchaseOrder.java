package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders")
@Schema(description = "Purchase order raised to a supplier to restock components")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated purchase order ID", example = "1")
    private Long purchaseOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    @Schema(description = "Supplier this order is placed with")
    private Supplier supplier;

    @Column(nullable = false)
    @Schema(description = "Date and time PO was created", example = "2026-04-11T10:30:00")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @Schema(description = "Total value of the purchase order", example = "360000.00")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current status of the purchase order", example = "PENDING",
            allowableValues = {
                    "PENDING", "APPROVED", "SHIPPED",
                    "RECEIVED", "CANCELLED"
            })
    private PurchaseOrderStatus status;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of product lines in this purchase order")
    private List<PurchaseOrderItem> items = new ArrayList<>();
}