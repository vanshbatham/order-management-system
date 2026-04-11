package com.oms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentSummaryReport {
    private String paymentMethod;
    private long transactionCount;
    private double totalAmount;
}