package com.oms.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "BOM entry showing a component required for a product")
public class BOMResponse {

    @Schema(description = "BOM entry ID", example = "1")
    private Long productBomId;

    @Schema(description = "Finished product ID", example = "1")
    private Long productId;

    @Schema(description = "Finished product name", example = "UPS System")
    private String productName;

    @Schema(description = "Component product ID", example = "2")
    private Long componentId;

    @Schema(description = "Component product name", example = "Battery Cell")
    private String componentName;

    @Schema(description = "Quantity of component required", example = "4")
    private Integer quantity;
}