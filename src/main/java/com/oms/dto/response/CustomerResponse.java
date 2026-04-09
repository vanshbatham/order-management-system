package com.oms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
}