package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(description = "Request body for creating a new sales order")
public class CreateOrderRequest {

    @Schema(description = "ID of the customer placing the order",
            example = "1")
    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @Schema(description = "List of products and quantities in this order")
    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<OrderItemRequest> items;
}