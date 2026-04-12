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
@Table(name = "sales_orders")
@Schema(description = "Sales order placed by a customer")
public class SalesOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated sales order ID", example = "1")
    private Long salesOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Schema(description = "Customer who placed this order")
    private Customer customer;

    @Column(nullable = false)
    @Schema(description = "Date and time the order was placed", example = "2026-04-11T10:30:00")
    private LocalDateTime orderDate;

    @Column(nullable = false)
    @Schema(description = "Total value of the order in rupees", example = "30000.00")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current status of the order", example = "CONFIRMED",
            allowableValues = {
                    "PENDING", "CONFIRMED", "PROCESSING",
                    "SHIPPED", "DELIVERED", "CANCELLED"
            })
    private OrderStatus status;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "List of product lines in this order")
    private List<SalesOrderItem> items = new ArrayList<>();
}