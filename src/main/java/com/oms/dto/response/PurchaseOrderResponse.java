package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "Purchase order details returned in API responses")
public class PurchaseOrderResponse {

    @Schema(description = "Unique purchase order ID", example = "1")
    private Long purchaseOrderId;

    @Schema(description = "Supplier ID", example = "1")
    private Long supplierId;

    @Schema(description = "Supplier name", example = "PowerTech Supplies")
    private String supplierName;

    @Schema(description = "Date and time PO was created")
    private LocalDateTime orderDate;

    @Schema(description = "Total PO value in rupees", example = "360000.00")
    private Double totalAmount;

    @Schema(description = "Current PO status", example = "PENDING",
            allowableValues = {
                    "PENDING", "APPROVED", "SHIPPED",
                    "RECEIVED", "CANCELLED"
            })
    private String status;

    @Schema(description = "List of items in this purchase order")
    private List<PurchaseOrderItemResponse> items;
}