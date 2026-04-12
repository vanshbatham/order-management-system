package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Lifecycle status of a sales order")
public enum OrderStatus {
    PENDING,
    CONFIRMED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}