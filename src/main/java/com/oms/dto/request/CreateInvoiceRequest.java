package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for generating an invoice from a sales order")
public class CreateInvoiceRequest {

    @Schema(description = "ID of the confirmed sales order to invoice", example = "1")
    @NotNull(message = "Sales order ID is required")
    private Long salesOrderId;
}