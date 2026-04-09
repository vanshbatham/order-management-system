package com.oms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BOMRequest {
    private Long productId;
    private Long componentId;
    private Integer quantity;
}