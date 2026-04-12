package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sales_order_items")
@Schema(description = "A single product line within a sales order")
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated order item ID", example = "1")
    private Long salesOrderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", nullable = false)
    @Schema(description = "The sales order this item belongs to")
    private SalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "The product being ordered")
    private Product product;

    @Column(nullable = false)
    @Schema(description = "Quantity of the product ordered", example = "2")
    private Integer quantity;

    @Column(nullable = false)
    @Schema(description = "Unit price at the time of order (price snapshot)", example = "15000.00")
    private Double unitPrice;
}