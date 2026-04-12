package com.oms.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request body for adding a component to a product's BOM")
public class BOMRequest {

    @Schema(description = "ID of the finished product", example = "1")
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Schema(description = "ID of the component/raw material", example = "2")
    @NotNull(message = "Component ID is required")
    private Long componentId;

    @Schema(description = "Quantity of this component required", example = "4")
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}