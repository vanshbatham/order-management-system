package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Lifecycle status of a purchase order")
public enum PurchaseOrderStatus {
    PENDING,
    APPROVED,
    SHIPPED,
    RECEIVED,
    CANCELLED
}