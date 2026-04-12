package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "A single product line in a purchase order")
public class PurchaseOrderItemResponse {

    @Schema(description = "PO item ID", example = "1")
    private Long purchaseOrderItemId;

    @Schema(description = "Product ID", example = "2")
    private Long productId;

    @Schema(description = "Product name", example = "Battery Cell")
    private String productName;

    @Schema(description = "Quantity ordered", example = "200")
    private Integer quantity;

    @Schema(description = "Negotiated unit price", example = "1800.00")
    private Double unitPrice;

    @Schema(description = "Subtotal for this line", example = "360000.00")
    private Double subtotal;
}