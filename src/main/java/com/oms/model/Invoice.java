package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "invoices")
@Schema(description = "Invoice generated for a confirmed sales order")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated invoice ID", example = "1")
    private Long invoiceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", nullable = false, unique = true)
    @Schema(description = "The sales order this invoice was generated for")
    private SalesOrder salesOrder;

    @Column(nullable = false)
    @Schema(description = "Date and time invoice was generated", example = "2026-04-11T12:00:00")
    private LocalDateTime invoiceDate;

    @Column(nullable = false)
    @Schema(description = "Total invoice amount in rupees", example = "30000.00")
    private Double totalAmount;

    @Column(nullable = false)
    @Schema(description = "Amount paid so far", example = "10000.00")
    private Double paidAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Current invoice status", example = "PARTIALLY_PAID",
            allowableValues = {
                    "PENDING", "SENT", "PARTIALLY_PAID", "PAID", "CANCELLED"
            })
    private InvoiceStatus status;
}