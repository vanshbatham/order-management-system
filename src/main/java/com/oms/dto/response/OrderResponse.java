package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "Sales order details returned in API responses")
public class OrderResponse {

    @Schema(description = "Unique sales order ID", example = "1")
    private Long salesOrderId;

    @Schema(description = "ID of the customer", example = "1")
    private Long customerId;

    @Schema(description = "Name of the customer", example = "ABC Corp")
    private String customerName;

    @Schema(description = "Date and time order was placed")
    private LocalDateTime orderDate;

    @Schema(description = "Total order value in rupees", example = "30000.00")
    private Double totalAmount;

    @Schema(description = "Current order status", example = "CONFIRMED",
            allowableValues = {
                    "PENDING", "CONFIRMED", "PROCESSING",
                    "SHIPPED", "DELIVERED", "CANCELLED"
            })
    private String status;

    @Schema(description = "List of items in this order")
    private List<OrderItemResponse> items;
}