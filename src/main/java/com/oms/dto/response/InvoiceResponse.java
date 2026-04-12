package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Schema(description = "Invoice details returned in API responses")
public class InvoiceResponse {

    @Schema(description = "Unique invoice ID", example = "1")
    private Long invoiceId;

    @Schema(description = "Associated sales order ID", example = "1")
    private Long salesOrderId;

    @Schema(description = "Customer name", example = "ABC Corp")
    private String customerName;

    @Schema(description = "Date invoice was generated")
    private LocalDateTime invoiceDate;

    @Schema(description = "Total invoice amount", example = "30000.00")
    private Double totalAmount;

    @Schema(description = "Amount paid so far", example = "10000.00")
    private Double paidAmount;

    @Schema(description = "Remaining outstanding amount", example = "20000.00")
    private Double outstandingAmount;

    @Schema(description = "Invoice status", example = "PARTIALLY_PAID",
            allowableValues = {
                    "PENDING", "SENT", "PARTIALLY_PAID",
                    "PAID", "CANCELLED"
            })
    private String status;
}