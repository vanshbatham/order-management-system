package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "A single product line within a sales order")
public class OrderItemResponse {

    @Schema(description = "Order item ID", example = "1")
    private Long orderItemId;

    @Schema(description = "Product ID", example = "1")
    private Long productId;

    @Schema(description = "Product name", example = "UPS System")
    private String productName;

    @Schema(description = "Quantity ordered", example = "2")
    private Integer quantity;

    @Schema(description = "Unit price at time of order", example = "15000.00")
    private Double unitPrice;

    @Schema(description = "Subtotal for this line item", example = "30000.00")
    private Double subtotal;
}