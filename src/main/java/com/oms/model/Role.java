package com.oms.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Available user roles in the system")
public enum Role {
    ADMINISTRATOR,
    BUSINESS_ANALYST,
    FINANCIAL_MANAGER,
    PURCHASING_OFFICER,
    PRODUCT_MANAGER,
    SALES_REPRESENTATIVE
}