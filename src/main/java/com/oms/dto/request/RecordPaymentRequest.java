package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for recording a payment against an invoice")
public class RecordPaymentRequest {

    @Schema(description = "ID of the invoice being paid", example = "1")
    @NotNull(message = "Invoice ID is required")
    private Long invoiceId;

    @Schema(description = "Payment amount in rupees", example = "10000.00")
    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Payment amount must be greater than 0")
    private Double amount;

    @Schema(description = "Method of payment", example = "BANK_TRANSFER",
            allowableValues = {
                    "CASH", "BANK_TRANSFER",
                    "CHEQUE", "CREDIT_CARD", "UPI"
            })
    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @Schema(description = "Optional notes about this payment", example = "First installment")
    private String notes;
}