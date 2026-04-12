package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payment status of an invoice")
public enum InvoiceStatus {
    PENDING,
    SENT,
    PARTIALLY_PAID,
    PAID,
    CANCELLED
}