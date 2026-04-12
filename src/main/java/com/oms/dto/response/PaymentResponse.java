package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "Payment record returned in API responses")
public class PaymentResponse {

    @Schema(description = "Unique payment ID", example = "1")
    private Long paymentId;

    @Schema(description = "Invoice this payment applies to", example = "1")
    private Long invoiceId;

    @Schema(description = "Date and time payment was recorded")
    private LocalDateTime paymentDate;

    @Schema(description = "Amount paid", example = "10000.00")
    private Double amount;

    @Schema(description = "Payment method used", example = "BANK_TRANSFER")
    private String paymentMethod;

    @Schema(description = "Optional notes", example = "First installment")
    private String notes;
}