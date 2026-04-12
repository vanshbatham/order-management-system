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
@Schema(description = "Request body for creating a purchase order with a supplier")
public class CreatePurchaseOrderRequest {

    @Schema(description = "ID of the supplier",
            example = "1")
    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @Schema(description = "List of products being ordered from supplier")
    @NotEmpty(message = "Purchase order must have at least one item")
    @Valid
    private List<PurchaseOrderItemRequest> items;
}