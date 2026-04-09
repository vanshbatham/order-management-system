package com.oms.controller;

import com.oms.dto.request.CustomerRequest;
import com.oms.dto.response.CustomerResponse;
import com.oms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR')")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        return new ResponseEntity<>(orderService.createCustomer(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return new ResponseEntity<>(orderService.getAllCustomers(), HttpStatus.OK);
    }
}