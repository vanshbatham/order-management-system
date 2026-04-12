package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase_order_items")
@Schema(description = "A single product line within a purchase order")
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated PO item ID", example = "1")
    private Long purchaseOrderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @Schema(description = "The purchase order this item belongs to")
    private PurchaseOrder purchaseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "The product being purchased")
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Quantity being ordered from supplier", example = "200")
    private Integer quantity;

    @Column(nullable = false)
    @Schema(description = "Negotiated unit price with supplier", example = "1800.00")
    private Double unitPrice;
}