package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "A single product line in a purchase order")
public class PurchaseOrderItemRequest {

    @Schema(description = "ID of the product to purchase", example = "2")
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Schema(description = "Quantity to order", example = "200")
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Schema(description = "Negotiated unit price with supplier", example = "1800.00")
    @NotNull(message = "Unit price is required")
    @Min(value = 0, message = "Unit price cannot be negative")
    private Double unitPrice;
}