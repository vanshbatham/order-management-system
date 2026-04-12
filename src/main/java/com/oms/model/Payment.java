package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payments")
@Schema(description = "Payment record made against an invoice")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Auto-generated payment ID", example = "1")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    @Schema(description = "The invoice this payment is applied to")
    private Invoice invoice;

    @Column(nullable = false)
    @Schema(description = "Date and time payment was recorded",
            example = "2026-04-11T14:00:00")
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    @Schema(description = "Amount paid in rupees", example = "10000.00")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(description = "Payment method used", example = "BANK_TRANSFER",
            allowableValues = {
                    "CASH", "BANK_TRANSFER",
                    "CHEQUE", "CREDIT_CARD", "UPI"
            })
    private PaymentMethod paymentMethod;

    @Schema(description = "Optional notes about this payment",
            example = "First installment")
    private String notes;
}