package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Accepted payment methods")
public enum PaymentMethod {
    CASH,
    BANK_TRANSFER,
    CHEQUE,
    CREDIT_CARD,
    UPI
}