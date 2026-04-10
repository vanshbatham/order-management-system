package com.oms.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInvoiceRequest {
    private Long salesOrderId;
}