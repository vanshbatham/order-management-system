package com.oms.controller;

import com.oms.dto.request.CreateOrderRequest;
import com.oms.dto.response.OrderResponse;
import com.oms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR')")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST', 'PRODUCT_MANAGER')")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR'," + "'BUSINESS_ANALYST', 'PRODUCT_MANAGER')")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'PRODUCT_MANAGER')")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return new ResponseEntity<>(orderService.updateOrderStatus(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SALES_REPRESENTATIVE', 'ADMINISTRATOR')")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity<>("Order cancelled successfully", HttpStatus.OK);
    }
}